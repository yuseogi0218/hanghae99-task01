package hanghae99.reboot.review.product.domain;

public class ProductBuilder {

    public static Product build() {
        Product product = new Product(1L);
        product.updateReviewInfo(15, 4.6f);

        return product;
    }

    public static Product build2() {
        Product product = new Product(2L);
        product.updateReviewInfo(10, 5.0f);

        return product;
    }
}
