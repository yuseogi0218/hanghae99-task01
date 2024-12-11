package hanghae99.reboot.review.product.unit.domain;

import hanghae99.reboot.review.product.domain.Review;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewUnitTest {
    @Test
    public void constructor() {
        // given
        Long productId = 1L;
        Long userId = 1L;
        Integer score = 5;
        String content = "리뷰 내용";
        String imageUrl = "/image.png";

        // when
        Review review = new Review(productId, userId, score, content, imageUrl);

        // then
        Assertions.assertThat(review.getProduct().getId()).isEqualTo(productId);
        Assertions.assertThat(review.getUserId()).isEqualTo(userId);
        Assertions.assertThat(review.getScore()).isEqualTo(score);
        Assertions.assertThat(review.getContent()).isEqualTo(content);
        Assertions.assertThat(review.getImageUrl()).isEqualTo(imageUrl);
    }
}
