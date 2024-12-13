package hanghae99.reboot.review.product.dto.response;

import hanghae99.reboot.review.product.domain.Product;

import java.util.List;

public record GetProductReviewsResponse(
        Integer totalCount,
        Float score,
        Integer cursor,
        List<GetReviewResponse> reviews
) {
    public static GetProductReviewsResponse from(Product product, List<GetReviewResponse> reviews) {
        Integer nextCursor;
        if (reviews.isEmpty()) {
            nextCursor = null;
        } else {
            nextCursor = reviews.get(reviews.size() - 1).id().intValue();
        }
        return new GetProductReviewsResponse(product.getReviewCount(), product.getScore(), nextCursor, reviews);
    }
}
