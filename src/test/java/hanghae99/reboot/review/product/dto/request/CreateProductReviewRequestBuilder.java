package hanghae99.reboot.review.product.dto.request;

public class CreateProductReviewRequestBuilder {
    public static CreateProductReviewRequest build() {
        return new CreateProductReviewRequest(1L, 5, "리뷰 내용");
    }

    public static CreateProductReviewRequest nullUserIdBuild() {
        return new CreateProductReviewRequest(null, 5, "리뷰 내용");
    }

    public static CreateProductReviewRequest nullScoreBuild() {
        return new CreateProductReviewRequest(1L, null, "리뷰 내용");
    }

    public static CreateProductReviewRequest invalidScoreBuild() {
        return new CreateProductReviewRequest(1L, 0, "리뷰 내용");
    }
}