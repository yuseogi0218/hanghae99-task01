package hanghae99.reboot.review.product.dto.response;

import hanghae99.reboot.review.product.domain.Product;
import hanghae99.reboot.review.product.domain.ProductBuilder;

import java.util.ArrayList;
import java.util.List;

public class GetProductReviewsResponseBuilder {
    public static GetProductReviewsResponse build() {
        Product product = ProductBuilder.build();
        GetReviewResponse review1 = GetReviewResponseBuilder.build1();
        GetReviewResponse review2 = GetReviewResponseBuilder.build2();

        return GetProductReviewsResponse.from(product, List.of(review2, review1));
    }

    public static GetProductReviewsResponse buildForEmptyList() {
        Product product = ProductBuilder.build();

        return GetProductReviewsResponse.from(product, new ArrayList<>());
    }
}
