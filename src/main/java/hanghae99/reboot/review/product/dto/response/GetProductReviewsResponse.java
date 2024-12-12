package hanghae99.reboot.review.product.dto.response;

import hanghae99.reboot.review.product.domain.Product;

import java.util.List;

public record GetProductReviewsResponse(
        Integer totalCount,
        Float score,
        Integer cursor,
        List<GetReviewResponse> reviews
) {
    public static GetProductReviewsResponse from(Product product, Integer cursor, List<GetReviewResponse> reviews) {
        return new GetProductReviewsResponse(product.getReviewCount(), product.getScore(), cursor - reviews.size(), reviews);
    }
}
