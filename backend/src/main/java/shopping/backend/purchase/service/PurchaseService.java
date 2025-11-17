package shopping.backend.purchase.service;

import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.stereotype.Service;
import shopping.backend.member.model.Member;
import shopping.backend.member.model.MemberId;
import shopping.backend.member.model.MemberRepository;
import shopping.backend.product.model.Product;
import shopping.backend.product.repository.ProductRepository;
import shopping.backend.purchase.dto.CartPurchaseAllRequest;
import shopping.backend.purchase.dto.CartPurchaseAllResponse;
import shopping.backend.purchase.dto.PurchaseItem;
import shopping.backend.purchase.dto.PurchaseRequest;
import shopping.backend.purchase.dto.PurchaseResponse;

@Service
@Transactional
public class PurchaseService {
    private MemberRepository memberRepository;
    private ProductRepository productRepository;

    public PurchaseService(MemberRepository memberRepository, ProductRepository productRepository) {
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }

    public PurchaseResponse purchase(PurchaseRequest request) {
        Member member = findMemberId(request)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보가 없습니다"));

        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new IllegalArgumentException("상품 정보가 없습니다."));
        int quantity = request.quantity();
        int totalPoint = product.price() * quantity;

        if (member.remain() < totalPoint) {
            throw new IllegalArgumentException("포인트가 부족합니다");
        }

        if (product.stock() < quantity) {
            throw new IllegalArgumentException("재고가 부족합니다");
        }
        member.usePoint(totalPoint);
        product.decreaseStock(quantity);
        return new PurchaseResponse(
                member.Id(),
                product.id(),
                quantity,
                totalPoint,
                member.remain(),
                product.stock()
        );
    }

    @Transactional
    public CartPurchaseAllResponse purchaseAll(CartPurchaseAllRequest request) {

        Member member = memberRepository.findById(new MemberId(request.memberId()))
                .orElseThrow(() -> new IllegalArgumentException("회원 정보가 없습니다."));

        int totalPrice = 0;

        for (PurchaseItem item : request.items()) {
            Product product = productRepository.findById(item.productId())
                    .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

            if (product.stock() < item.quantity()) {
                throw new IllegalArgumentException("재고가 부족한 상품이 있습니다: " + product.name());
            }

            totalPrice += product.price() * item.quantity();
        }

        if (member.remain() < totalPrice) {
            throw new IllegalArgumentException("포인트가 부족합니다.");
        }

        for (PurchaseItem item : request.items()) {
            Product product = productRepository.findById(item.productId()).get();
            product.decreaseStock(item.quantity());
        }

        member.usePoint(totalPrice);

        return new CartPurchaseAllResponse(totalPrice, member.remain());
    }

    private Optional<Member> findMemberId(PurchaseRequest request) {
        return memberRepository.findById(new MemberId(request.memberId()));
    }
}
