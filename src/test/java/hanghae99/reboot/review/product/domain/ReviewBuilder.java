package hanghae99.reboot.review.product.domain;

import org.assertj.core.api.Assertions;

public class ReviewBuilder {
    public static Review build() {
        return Review
                .builder()
                .productId(1L)
                .userId(1L)
                .score(5)
                .content("리뷰 1 내용")
                .imageUrl("/image.png")
                .build();
    }

    public static void assertReview(Review actualReview, Review expectedReview) {
        Assertions.assertThat(actualReview.getProduct().getId()).isEqualTo(expectedReview.getProduct().getId());
        Assertions.assertThat(actualReview.getUserId()).isEqualTo(expectedReview.getUserId());
        Assertions.assertThat(actualReview.getScore()).isEqualTo(expectedReview.getScore());
        Assertions.assertThat(actualReview.getContent()).isEqualTo(expectedReview.getContent());
        Assertions.assertThat(actualReview.getImageUrl()).isEqualTo(expectedReview.getImageUrl());
    }
}
