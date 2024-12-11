package hanghae99.reboot.review.product.dto.response;

import java.util.List;

public class GetProductReviewsResponseBuilder {
    public static GetProductReviewsResponse build() {
        GetReviewResponse review1 = GetReviewResponseBuilder.build1();
        GetReviewResponse review2 = GetReviewResponseBuilder.build2();

        return new GetProductReviewsResponse(2, 4.0f, 1, List.of(review1, review2));
    }
}
