package hanghae99.reboot.review.product.dto.response;

import hanghae99.reboot.review.product.domain.Product;
import hanghae99.reboot.review.product.domain.ProductBuilder;

import java.util.List;

public class GetProductReviewsResponseBuilder {
    public static GetProductReviewsResponse build() {
        Product product = ProductBuilder.build();
        GetReviewResponse review1 = GetReviewResponseBuilder.build1();
        GetReviewResponse review2 = GetReviewResponseBuilder.build2();

        return GetProductReviewsResponse.from(product, 1, List.of(review1, review2));
    }
}
