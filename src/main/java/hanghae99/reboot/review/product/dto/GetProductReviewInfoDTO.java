package hanghae99.reboot.review.product.dto;

public record GetProductReviewInfoDTO(
        Integer totalCount,
        Float score
) {
    public GetProductReviewInfoDTO(Long totalCount, Double score) {
        this(totalCount.intValue(), score.floatValue());
    }
}
