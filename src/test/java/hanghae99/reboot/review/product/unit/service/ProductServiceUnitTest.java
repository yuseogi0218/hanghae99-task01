package hanghae99.reboot.review.product.unit.service;

import hanghae99.reboot.review.common.ServiceUnitTest;
import hanghae99.reboot.review.common.exception.CustomCommonException;
import hanghae99.reboot.review.product.domain.Product;
import hanghae99.reboot.review.product.domain.ProductBuilder;
import hanghae99.reboot.review.product.exception.ProductErrorCode;
import hanghae99.reboot.review.product.repository.ProductRepository;
import hanghae99.reboot.review.product.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class ProductServiceUnitTest extends ServiceUnitTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    /**
     * 상품 DB Id 로 상품 조회 성공
     */
    @Test
    public void getProductById_성공() {
        // given
        Long productId = 1L;
        Product expectedProduct = ProductBuilder.build();

        // stub
        when(productRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));

        // when
        Product actualProduct = productService.getProductById(productId);

        // then
        Assertions.assertThat(actualProduct).isEqualTo(expectedProduct);
    }

    /**
     * 상품 DB Id 로 상품 조회 실패
     * - 실패 사유 : 상품 DB Id에 해당하는 상품이 존재하지 않습니다.
     */
    @Test
    public void getProductById_실패_NOT_FOUND_PRODUCT() {
        // given
        Long unknownProductId = 0L;

        // stub
        when(productRepository.findById(unknownProductId)).thenReturn(Optional.empty());

        // when
        Assertions.assertThatThrownBy(() -> productService.getProductById(unknownProductId))
                .isInstanceOf(CustomCommonException.class)
                .hasMessage(ProductErrorCode.NOT_FOUND_PRODUCT.getMessage());
    }
}
