package hanghae99.reboot.review.product.dto.response;

import java.time.LocalDateTime;

public class GetReviewResponseBuilder {
    public static GetReviewResponse build1() {
        return new GetReviewResponse(1L, 1L, 5, "리뷰 1 내용", "/image.png", LocalDateTime.of(2024, 12, 2, 10, 20, 30, 444));
    }

    public static GetReviewResponse build2() {
        return new GetReviewResponse(2L, 1L, 3, "리뷰 2 내용", null, LocalDateTime.of(2024, 12, 1, 10, 20, 30, 444));
    }

}
