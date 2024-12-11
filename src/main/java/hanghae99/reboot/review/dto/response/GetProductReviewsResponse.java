package hanghae99.reboot.review.dto.response;

import java.util.List;

public record GetProductReviewsResponse(
        Integer totalCount,
        Double score,
        Integer cursor,
        List<GetReviewResponse> reviews
) { }
