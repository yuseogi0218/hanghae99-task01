package hanghae99.reboot.review.product.unit.api;

import hanghae99.reboot.review.common.exception.CommonErrorCode;
import hanghae99.reboot.review.common.APIUnitTest;
import hanghae99.reboot.review.product.api.ReviewAPI;
import hanghae99.reboot.review.product.dto.response.GetProductReviewsResponse;
import hanghae99.reboot.review.product.dto.response.GetProductReviewsResponseBuilder;
import hanghae99.reboot.review.product.service.ReviewService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewAPI.class)
public class ReviewAPIUnitTest extends APIUnitTest {


    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp() {
        mvc = buildMockMvc(context);
    }

    @MockitoBean
    private ReviewService reviewService;

    /**
     * 상품 리뷰 목록 조회 성공
     */
    @Test
    public void 상품_리뷰_목록_조회_성공() throws Exception {
        // given
        final String productId = "1";
        final String cursor = "1";
        final String size = "10";

        final GetProductReviewsResponse expectedResponse = GetProductReviewsResponseBuilder.build();

        // stub
        when(reviewService.getProductReviews(any(), any())).thenReturn(expectedResponse);

        // when
        final ResultActions resultActions = requestGetProductReviews(productId, cursor, size);

        // then
        final String responseString = resultActions
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        final GetProductReviewsResponse actualResponse = objectMapper.readValue(responseString, GetProductReviewsResponse.class);

        Assertions.assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    /**
     * 상품 리뷰 목록 조회 실패
     * - 실패 사유 : productId 파라미터 타입
     */
    @Test
    public void 상품_리뷰_목록_조회_실패_productId_파라미터_타입() throws Exception {
        // given
        final String invalidProductId = "invalid-product-id";
        final String cursor = "1";
        final String size = "10";

        // when
        final ResultActions resultActions = requestGetProductReviews(invalidProductId, cursor, size);

        // then
        assertErrorWithMessage(CommonErrorCode.MISMATCH_PARAMETER_TYPE, resultActions, "productId");
    }

    /**
     * 상품 리뷰 목록 조회 실패
     * - 실패 사유 : cursor 파라미터 타입
     */
    @Test
    public void 상품_리뷰_목록_조회_실패_cursor_파라미터_타입() throws Exception {
        // given
        final String productId = "1";
        final String invalidCursor = "invalid-cursor";
        final String size = "10";

        // when
        final ResultActions resultActions = requestGetProductReviews(productId, invalidCursor, size);

        // then
        assertErrorWithMessage(CommonErrorCode.MISMATCH_PARAMETER_TYPE, resultActions, "cursor");
    }

    /**
     * 상품 리뷰 목록 조회 실패
     * - 실패 사유 : size 파라미터 타입
     */
    @Test
    public void 상품_리뷰_목록_조회_실패_size_파라미터_타입() throws Exception {
        // given
        final String productId = "1";
        final String cursor = "1";
        final String invalidSize = "invalid-size";

        // when
        final ResultActions resultActions = requestGetProductReviews(productId, cursor, invalidSize);

        // then
        assertErrorWithMessage(CommonErrorCode.MISMATCH_PARAMETER_TYPE, resultActions, "size");
    }

    private ResultActions requestGetProductReviews(String productId, String cursor, String size) throws Exception {
        return mvc.perform(get("/products/{productId}/reviews", productId)
                        .param("cursor", cursor)
                        .param("size", size))
                .andDo(print());
    }
}
