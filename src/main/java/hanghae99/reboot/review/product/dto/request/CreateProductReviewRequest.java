package hanghae99.reboot.review.product.dto.request;


import hanghae99.reboot.review.product.domain.Review;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateProductReviewRequest(
        @NotNull(message = "사용자 Id 는 필수 입력값 입니다.")
        Long userId,
        @Min(value = 1, message = "리뷰 점수는 1 ~ 5 사이의 정수 이어야 합니다.")
        @Max(value = 5, message = "리뷰 점수는 1 ~ 5 사이의 정수 이어야 합니다.")
        @NotNull(message = "리뷰 점수는 필수 입력값 입니다.")
        Integer score,
        String content
) {
        public Review toEntity(Long productId, String fileUrl) {
                return Review.builder()
                        .productId(productId)
                        .userId(userId)
                        .score(score)
                        .content(content)
                        .imageUrl(fileUrl)
                        .build();
        }
}
