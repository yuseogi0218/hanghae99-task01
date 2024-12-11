package hanghae99.reboot.review.product.domain;

public class ReviewBuilder {
    public static Review build() {
        return Review
                .builder()
                .productId(1L)
                .userId(1L)
                .score(5)
                .content("리뷰 내용")
                .imageUrl("/image.png")
                .build();
    }
}
