package hanghae99.reboot.review.product.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetReviewResponseBuilder {

    private final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static GetReviewResponse build1() {
        return new GetReviewResponse(1L, 1L, 5, "리뷰 1 내용", "/image.png", LocalDateTime.parse("2024-12-01 10:20:30.000", dateTimeFormatter));
    }

    public static GetReviewResponse build2() {
        return new GetReviewResponse(2L, 2L, 5, "리뷰 2 내용", "/image.png", LocalDateTime.parse("2024-12-01 10:20:30.001", dateTimeFormatter));
    }

}
