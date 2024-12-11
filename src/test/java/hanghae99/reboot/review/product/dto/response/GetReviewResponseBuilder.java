package hanghae99.reboot.review.product.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetReviewResponseBuilder {

    private final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static GetReviewResponse build1() {
        return new GetReviewResponse(15L, 1L, 5, "리뷰 15 내용", "/image.png", LocalDateTime.parse("2024-12-01 10:20:30.014", dateTimeFormatter));
    }

    public static GetReviewResponse build2() {
        return new GetReviewResponse(14L, 1L, 4, "리뷰 14 내용", "/image.png", LocalDateTime.parse("2024-12-01 10:20:30.013", dateTimeFormatter));
    }

}
