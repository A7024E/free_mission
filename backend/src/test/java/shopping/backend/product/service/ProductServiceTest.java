package shopping.backend.product.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shopping.backend.product.dto.ProductListResponse;
import shopping.backend.product.model.Category;
import shopping.backend.product.model.Description;
import shopping.backend.product.model.Price;
import shopping.backend.product.model.Product;
import shopping.backend.product.model.ProductName;
import shopping.backend.product.model.Stock;
import shopping.backend.product.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    private Product sampleProduct() {
        return new Product(
                new ProductName("3D 고양이"),
                new Price(5000),
                new Stock(20),
                Category.ANIMAL,
                new Description("고양이는 귀엽다")
        );
    }

    @Test
    @DisplayName("전체 상품 조회 성공")
    void findAll_success() {
        // given
        when(productRepository.findAll()).thenReturn(List.of(sampleProduct()));

        // when
        List<ProductListResponse> list = productService.findAll();

        // then
        assertEquals(1, list.size());
        assertEquals("3D 고양이", list.get(0).name());
        verify(productRepository, times(1)).findAll();
    }

}
