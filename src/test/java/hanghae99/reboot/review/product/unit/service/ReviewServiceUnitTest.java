package hanghae99.reboot.review.product.unit.service;

import hanghae99.reboot.review.common.ServiceUnitTest;
import hanghae99.reboot.review.common.exception.CustomCommonException;
import hanghae99.reboot.review.common.file.FileService;
import hanghae99.reboot.review.product.domain.Product;
import hanghae99.reboot.review.product.domain.ProductBuilder;
import hanghae99.reboot.review.product.domain.Review;
import hanghae99.reboot.review.product.domain.ReviewBuilder;
import hanghae99.reboot.review.product.dto.GetProductReviewInfoDTO;
import hanghae99.reboot.review.product.dto.GetProductReviewInfoDTOBuilder;
import hanghae99.reboot.review.product.dto.request.CreateProductReviewRequest;
import hanghae99.reboot.review.product.dto.request.CreateProductReviewRequestBuilder;
import hanghae99.reboot.review.product.dto.response.GetProductReviewsResponse;
import hanghae99.reboot.review.product.dto.response.GetProductReviewsResponseBuilder;
import hanghae99.reboot.review.product.exception.ProductErrorCode;
import hanghae99.reboot.review.product.repository.ReviewRepository;
import hanghae99.reboot.review.product.service.ProductService;
import hanghae99.reboot.review.product.service.ReviewService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReviewServiceUnitTest extends ServiceUnitTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ProductService productService;

    @Mock
    private FileService fileService;

    /**
     * 상품 리뷰 목록 조회 성공
     */
    @Test
    public void getProductReviews_성공() {
        // given
        Long productId = 1L;
        Integer cursor = 3;
        Integer size = 2;

        Product product = ProductBuilder.build();
        GetProductReviewsResponse expectedResponse = GetProductReviewsResponseBuilder.build();

        // stub
        when(productService.getProductById(productId)).thenReturn(product);
        when(reviewRepository.findOrderByCreatedAtDesc(productId, cursor, PageRequest.of(0, size))).thenReturn(expectedResponse.reviews());

        // when
        GetProductReviewsResponse actualResponse = reviewService.getProductReviews(productId, cursor, size);

        // then
        Assertions.assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    /**
     * 상품 리뷰 등록 성공
     */
    @Test
    public void createProductReview_성공() {
        // given
        Long productId = 1L;
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "image.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                new byte[]{65}
        );
        CreateProductReviewRequest request = CreateProductReviewRequestBuilder.build();

        Product product = ProductBuilder.build();
        GetProductReviewInfoDTO productReviewInfoDTO = GetProductReviewInfoDTOBuilder.build();

        // stub
        when(productService.getProductById(productId)).thenReturn(product);
        when(reviewRepository.findTopByProductIdAndUserId(productId, request.userId())).thenReturn(Optional.empty());

        // when
        reviewService.createProductReview(productId, file, request);

        // then
        verify(fileService, times(1)).uploadFile(file);
        verify(reviewRepository, times(1)).save(any());
        Assertions.assertThat(product.getReviewCount()).isEqualTo(productReviewInfoDTO.totalCount());
        Assertions.assertThat(product.getScore()).isEqualTo(productReviewInfoDTO.score());
    }

    /**
     * 상품 리뷰 등록 실패
     * - 실패 사유 : 해당 상품에 이미 리뷰를 작성하였음
     */
    @Test
    public void createProductReview_실패_ALREADY_REVIEWED_PRODUCT() {
        // given
        Long productId = 1L;
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "image.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                new byte[]{65}
        );
        CreateProductReviewRequest request = CreateProductReviewRequestBuilder.build();

        Product product = ProductBuilder.build();
        Review review = ReviewBuilder.build();

        // stub
        when(productService.getProductById(productId)).thenReturn(product);
        when(reviewRepository.findTopByProductIdAndUserId(productId, request.userId())).thenReturn(Optional.of(review));

        // when & then
        Assertions.assertThatThrownBy(() -> reviewService.createProductReview(productId, file, request))
                .isInstanceOf(CustomCommonException.class)
                .hasMessage(ProductErrorCode.ALREADY_REVIEWED_PRODUCT.getMessage());
    }
}
