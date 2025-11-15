package shopping.backend.purchase.service;

import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.stereotype.Service;
import shopping.backend.member.model.Member;
import shopping.backend.member.model.MemberId;
import shopping.backend.member.model.MemberRepository;
import shopping.backend.product.model.Product;
import shopping.backend.product.repository.ProductRepository;
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

    private Optional<Member> findMemberId(PurchaseRequest request) {
        return memberRepository.findById(new MemberId(request.memberId()));
    }
}
