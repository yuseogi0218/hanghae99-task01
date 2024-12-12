package hanghae99.reboot.review.product.integration;

import hanghae99.reboot.review.common.IntegrationTest;
import hanghae99.reboot.review.product.dto.request.CreateProductReviewRequest;
import hanghae99.reboot.review.product.dto.request.CreateProductReviewRequestBuilder;
import hanghae99.reboot.review.product.dto.response.GetProductReviewsResponse;
import hanghae99.reboot.review.product.dto.response.GetProductReviewsResponseBuilder;
import hanghae99.reboot.review.product.exception.ProductErrorCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReviewIntegrationTest extends IntegrationTest {

    /**
     * 상품 리뷰 목록 조회 성공
     */
    @Test
    public void 상품_리뷰_목록_조회_성공() throws Exception {
        // given
        String productId = "1";
        String cursor = "1";
        String size = "2";

        GetProductReviewsResponse expectedResponse = GetProductReviewsResponseBuilder.build();

        // when
        ResultActions resultActions = requestGetProductReviews(productId, cursor, size);

        // then
        String responseString = resultActions
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        GetProductReviewsResponse actualResponse = objectMapper.readValue(responseString, GetProductReviewsResponse.class);

        Assertions.assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    /**
     * 상품 리뷰 목록 조회 실패
     * - 실패 사유 : 상품 DB Id에 해당하는 상품이 존재하지 않습니다.
     */
    @Test
    public void 상품_리뷰_목록_조회_실패_상품_존재_X() throws Exception {
        // given
        String unknownProductId = "0";
        String cursor = "1";
        String size = "2";

        // when
        ResultActions resultActions = requestGetProductReviews(unknownProductId, cursor, size);

        // then
        assertError(ProductErrorCode.NOT_FOUND_PRODUCT, resultActions);
    }

    /**
     * 상품 리뷰 등록 성공
     */
    @Test
    public void 상품_리뷰_등록_성공() throws Exception {
        // given
        String productId = "1";
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "image.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                new byte[]{65}
        );
        CreateProductReviewRequest request = CreateProductReviewRequestBuilder.build();

        // when
        ResultActions resultActions = requestCreateProductionReview(productId, file, request);

        // then
        resultActions.andExpect(status().isOk());
    }

    /**
     * 상품 리뷰 등록 실패
     * - 실패 사유 : 상품 DB Id에 해당하는 상품이 존재하지 않습니다.
     */
    @Test
    public void 상품_리뷰_등록_실패_상품_존재_X() throws Exception {
        // given
        String unknownProductId = "0";
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "image.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                new byte[]{65}
        );
        CreateProductReviewRequest request = CreateProductReviewRequestBuilder.build();

        // when
        ResultActions resultActions = requestCreateProductionReview(unknownProductId, file, request);

        // then
        assertError(ProductErrorCode.NOT_FOUND_PRODUCT, resultActions);
    }

    /**
     * 상품 리뷰 등록 실패
     * - 실패 사유 : 해당 상품에 이미 리뷰를 작성한 사용자
     */
    @Test
    public void 상품_리뷰_등록_실패_리뷰_중복() throws Exception {
        // given
        String productId = "1";
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "image.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                new byte[]{65}
        );
        CreateProductReviewRequest request = CreateProductReviewRequestBuilder.duplicatedBuild();

        // when
        ResultActions resultActions = requestCreateProductionReview(productId, file, request);

        // then
        assertError(ProductErrorCode.ALREADY_REVIEWED_PRODUCT, resultActions);
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
