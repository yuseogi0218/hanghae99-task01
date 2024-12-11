package hanghae99.reboot.review.product.unit.api;

import hanghae99.reboot.review.common.exception.CommonErrorCode;
import hanghae99.reboot.review.common.APIUnitTest;
import hanghae99.reboot.review.product.api.ReviewAPI;
import hanghae99.reboot.review.product.dto.request.CreateProductReviewRequest;
import hanghae99.reboot.review.product.dto.request.CreateProductReviewRequestBuilder;
import hanghae99.reboot.review.product.dto.response.GetProductReviewsResponse;
import hanghae99.reboot.review.product.dto.response.GetProductReviewsResponseBuilder;
import hanghae99.reboot.review.product.service.ReviewService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileInputStream;
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

    /**
     * 상품 리뷰 등록 성공
     */
    @Test
    public void 상품_리뷰_등록_성공() throws Exception {
        // given
        final String productId = "1";
        final MockMultipartFile file = new MockMultipartFile(
                "file",
                "review.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                new byte[]{65}
        );
        final CreateProductReviewRequest request = CreateProductReviewRequestBuilder.build();

        // when
        final ResultActions resultActions = requestCreateProductionReview(productId, file, request);

        // then
        resultActions.andExpect(status().isOk());
    }

    /**
     * 상품 리뷰 등록 실패
     * - 실패 사유 : productId 파라미터 타입
     */
    @Test
    public void 상품_리뷰_등록_실패_productId_파라미터_타입() throws Exception {
        // given
        final String invalidProductId = "invalid-product-id";
        final MockMultipartFile file = new MockMultipartFile(
                "file",
                "review.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                new byte[]{65}
        );
        final CreateProductReviewRequest request = CreateProductReviewRequestBuilder.build();

        // when
        final ResultActions resultActions = requestCreateProductionReview(invalidProductId, file, request);

        // then
        assertErrorWithMessage(CommonErrorCode.MISMATCH_PARAMETER_TYPE, resultActions, "productId");
    }

    /**
     * 상품 리뷰 등록 실패
     * - 실패 사유 : 리뷰 내용 전송 X
     */
    @Test
    public void 상품_리뷰_등록_실패_리뷰_파라미터_null() throws Exception {
        // given
        final String productId = "1";
        final MockMultipartFile file = new MockMultipartFile(
                "file",
                "review.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                new byte[]{65}
        );

        // when
        final ResultActions resultActions = requestCreateProductionReview(productId, file, null);

        // then
        assertErrorWithMessage(CommonErrorCode.REQUIRED_PARAMETER, resultActions, "review");
    }

    /**
     * 상품 리뷰 등록 실패
     * - 실패 사유 : 사용자 Id 필드 null
     */
    @Test
    public void 상품_리뷰_등록_실패_userId_필드_null() throws Exception {
        // given
        final String productId = "1";
        final MockMultipartFile file = new MockMultipartFile(
                "file",
                "review.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                new byte[]{65}
        );
        final CreateProductReviewRequest request = CreateProductReviewRequestBuilder.nullUserIdBuild();

        // when
        final ResultActions resultActions = requestCreateProductionReview(productId, file, request);

        // then
        assertErrorWithMessage(CommonErrorCode.INVALID_REQUEST_BODY_FIELDS, resultActions, "사용자 Id 는 필수 입력값 입니다.");
    }

    /**
     * 상품 리뷰 등록 실패
     * - 실패 사유 : 리뷰 점수 필드 null
     */
    @Test
    public void 상품_리뷰_등록_실패_score_필드_null() throws Exception {
        // given
        final String productId = "1";
        final MockMultipartFile file = new MockMultipartFile(
                "file",
                "review.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                new byte[]{65}
        );
        final CreateProductReviewRequest request = CreateProductReviewRequestBuilder.nullScoreBuild();

        // when
        final ResultActions resultActions = requestCreateProductionReview(productId, file, request);

        // then
        assertErrorWithMessage(CommonErrorCode.INVALID_REQUEST_BODY_FIELDS, resultActions, "리뷰 점수는 필수 입력값 입니다.");
    }

    /**
     * 상품 리뷰 등록 실패
     * - 실패 사유 : 리뷰 점수 필드 유효성
     */
    @Test
    public void 상품_리뷰_등록_실패_score_필드_유효성() throws Exception {
        // given
        final String productId = "1";
        final MockMultipartFile file = new MockMultipartFile(
                "file",
                "review.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                new byte[]{65}
        );
        final CreateProductReviewRequest request = CreateProductReviewRequestBuilder.invalidScoreBuild();

        // when
        final ResultActions resultActions = requestCreateProductionReview(productId, file, request);

        // then
        assertErrorWithMessage(CommonErrorCode.INVALID_REQUEST_BODY_FIELDS, resultActions, "리뷰 점수는 1 ~ 5 사이의 정수 이어야 합니다.");
    }

    private ResultActions requestGetProductReviews(String productId, String cursor, String size) throws Exception {
        return mvc.perform(get("/products/{productId}/reviews", productId)
                        .param("cursor", cursor)
                        .param("size", size))
                .andDo(print());
    }

    private ResultActions requestCreateProductionReview(String productId, MockMultipartFile file, CreateProductReviewRequest request) throws Exception{
        MockMultipartHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/products/{productId}/reviews", productId);

        String requestJson = objectMapper.writeValueAsString(request);
        MockMultipartFile review = new MockMultipartFile("review", "review", MediaType.APPLICATION_JSON_VALUE, requestJson.getBytes(StandardCharsets.UTF_8));

        return mvc.perform(builder
                        .file(file)
                        .file(review)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
}
