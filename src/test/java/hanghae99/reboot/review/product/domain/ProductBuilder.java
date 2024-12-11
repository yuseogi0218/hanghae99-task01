package hanghae99.reboot.review.product.domain;

public class ProductBuilder {

    public static Product build() {
        Product product = new Product(1L);
        product.updateReview(10, 4.5f);

        return product;
    }
}
