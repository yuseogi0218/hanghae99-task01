package hanghae99.reboot.review.product.unit.service;

import hanghae99.reboot.review.common.ServiceUnitTest;
import hanghae99.reboot.review.product.domain.Product;
import hanghae99.reboot.review.product.domain.ProductBuilder;
import hanghae99.reboot.review.product.dto.response.GetProductReviewsResponse;
import hanghae99.reboot.review.product.dto.response.GetProductReviewsResponseBuilder;
import hanghae99.reboot.review.product.repository.ReviewRepository;
import hanghae99.reboot.review.product.service.ProductService;
import hanghae99.reboot.review.product.service.ReviewService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;

import static org.mockito.Mockito.when;

public class ReviewServiceUnitTest extends ServiceUnitTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ProductService productService;

    /**
     * 상품 DB Id 로 상품의 리뷰 목록 조회 성공
     */
    @Test
    public void getProductReviews_성공() {
        // given
        Long productId = 1L;
        Integer cursor = 1;
        Integer size = 2;

        Product product = ProductBuilder.build();
        GetProductReviewsResponse expectedResponse = GetProductReviewsResponseBuilder.build();

        // stub
        when(productService.getProductById(productId)).thenReturn(product);
        when(reviewRepository.findOrderByCreatedAtDesc(productId, PageRequest.of(0, size))).thenReturn(expectedResponse.reviews());

        // when
        GetProductReviewsResponse actualResponse = reviewService.getProductReviews(productId, cursor, size);

        // then
        Assertions.assertThat(actualResponse).isEqualTo(expectedResponse);
    }
}
