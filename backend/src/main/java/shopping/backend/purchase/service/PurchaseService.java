package shopping.backend.purchase.service;

import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.stereotype.Service;
import shopping.backend.exception.MemberException;
import shopping.backend.exception.ProductException;
import shopping.backend.exception.PurchaseException;
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
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public PurchaseService(MemberRepository memberRepository, ProductRepository productRepository) {
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }

    public PurchaseResponse purchase(PurchaseRequest request) {
        Member member = findMemberId(request.memberId());
        Product product = findProductId(request);


        validateEnoughPoint(member, product.price() * request.quantity());
        validateStock(product, request.quantity());

        member.usePoint(product.price() * request.quantity());
        product.decreaseStock(request.quantity());

        return new PurchaseResponse(
                member.Id(),
                product.id(),
                request.quantity(),
                product.price() * request.quantity(),
                member.remain(),
                product.stock()
        );
    }


    @Transactional
    public CartPurchaseAllResponse purchaseAll(CartPurchaseAllRequest request) {
        Member member = memberRepository.findById(new MemberId(request.memberId()))
                .orElseThrow(
                        () -> new IllegalArgumentException(MemberException.EXCEPTION_VALID_NOT_FOUND_MEMBER.message()));

        int totalPrice = 0;

        for (PurchaseItem item : request.items()) {
            Product product = productRepository.findById(item.productId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            ProductException.EXCEPTION_VALID_NOT_FOUND_PRODUCT.message()));

            if (product.stock() < item.quantity()) {
                throw new IllegalArgumentException(
                        PurchaseException.EXCEPTION_INVALID_ENOUGH_ALERT.message() + product.name());
            }

            totalPrice += product.price() * item.quantity();
        }

        if (member.remain() < totalPrice) {
            throw new IllegalArgumentException(PurchaseException.EXCEPTION_INVALID_ENOUGH_POINT.message());
        }

        for (PurchaseItem item : request.items()) {
            Product product = productRepository.findById(item.productId()).get();
            product.decreaseStock(item.quantity());
        }

        member.usePoint(totalPrice);

        return new CartPurchaseAllResponse(totalPrice, member.remain());
    }

    private Member findMemberId(String memberId) {
        return memberRepository.findById(new MemberId(memberId))
                .orElseThrow(() -> new IllegalArgumentException(
                        MemberException.EXCEPTION_VALID_NOT_FOUND_MEMBER.message()
                ));
    }

    private Product findProductId(PurchaseRequest request) {
        return productRepository.findById(request.productId())
                .orElseThrow(() -> new IllegalArgumentException(
                        ProductException.EXCEPTION_VALID_NOT_FOUND_PRODUCT.message()));
    }

    private void validateEnoughPoint(Member member, int required) {
        if (member.remain() < required) {
            throw new IllegalArgumentException(
                    PurchaseException.EXCEPTION_INVALID_ENOUGH_POINT.message()
            );
        }
    }

    private void validateStock(Product product, int required) {
        if (product.stock() < required) {
            throw new IllegalArgumentException(
                    PurchaseException.EXCEPTION_INVALID_ENOUGH_INVENTORY.message()
            );
        }
    }
}
