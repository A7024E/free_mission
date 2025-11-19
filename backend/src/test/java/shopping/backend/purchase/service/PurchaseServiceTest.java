package shopping.backend.purchase.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import shopping.backend.member.model.Gender;
import shopping.backend.member.model.Member;
import shopping.backend.member.model.MemberId;
import shopping.backend.member.model.MemberRepository;
import shopping.backend.member.model.NickName;
import shopping.backend.member.model.Password;
import shopping.backend.member.model.Point;
import shopping.backend.product.model.Category;
import shopping.backend.product.model.Description;
import shopping.backend.product.model.Price;
import shopping.backend.product.model.Product;
import shopping.backend.product.model.ProductName;
import shopping.backend.product.model.Stock;
import shopping.backend.product.repository.ProductRepository;
import shopping.backend.purchase.dto.PurchaseRequest;
import shopping.backend.purchase.dto.PurchaseResponse;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceTest {
    @Mock
    MemberRepository memberRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    PurchaseService purchaseService;

    private Member sampleMember() {
        Member member = new Member(
                new MemberId("test"),
                new Password("123456789"),
                new NickName("테스트"),
                Gender.MALE
        );
        return member;
    }

    private Product sampleProduct() {
        Product product = new Product(
                new ProductName("3D 고양이"),
                new Price(5000),
                new Stock(10),
                Category.ANIMAL,
                new Description("귀여운 고양이 3D")
        );
        ReflectionTestUtils.setField(product, "id", 1L);

        return product;
    }

    @Test
    @DisplayName("단일 구매 성공")
    void purchase_success() {
        // given
        Member member = sampleMember();
        Product product = sampleProduct();

        when(memberRepository.findById(new MemberId("test"))).thenReturn(Optional.of(member));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        PurchaseRequest request = new PurchaseRequest("test", 1L, 2);

        // when
        PurchaseResponse response = purchaseService.purchase(request);

        // then
        assertEquals("test", response.memberId());
        assertEquals(1L, response.productId());
        assertEquals(2, response.quantity());
        assertEquals(10000, response.totalPrice());
        assertEquals(40000, response.remainPoint());

        verify(memberRepository, times(1)).findById(new MemberId("test"));
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("단일 구매 실패 - 회원 없음")
    void purchase_fail_no_member() {
        when(memberRepository.findById(new MemberId("Pobi"))).thenReturn(Optional.empty());

        PurchaseRequest request = new PurchaseRequest("Pobi", 1L, 1);

        assertThrows(IllegalArgumentException.class,
                () -> purchaseService.purchase(request));
    }

    @Test
    @DisplayName("단일 구매 실패 - 상품 없음")
    void purchase_fail_no_product() {
        Member member = sampleMember();
        when(memberRepository.findById(new MemberId("test"))).thenReturn(Optional.of(member));
        when(productRepository.findById(999L)).thenReturn(Optional.empty());

        PurchaseRequest request = new PurchaseRequest("test", 999L, 1);

        assertThrows(IllegalArgumentException.class,
                () -> purchaseService.purchase(request));
    }

    @Test
    @DisplayName("단일 구매 실패 - 포인트 부족")
    void purchase_fail_not_enough_point() {
        Member member = sampleMember();
        Product expensive = new Product(
                new ProductName("비싼거"),
                new Price(60000),
                new Stock(10),
                Category.OBJECT,
                new Description("비싼 상품")
        );

        when(memberRepository.findById(new MemberId("test"))).thenReturn(Optional.of(member));
        when(productRepository.findById(1L)).thenReturn(Optional.of(expensive));

        PurchaseRequest request = new PurchaseRequest("test", 1L, 1);

        assertThrows(IllegalArgumentException.class,
                () -> purchaseService.purchase(request));
    }

}
