package hanghae99.reboot.review.product.domain;

public class ProductBuilder {

    public static Product build() {
        Product product = new Product(1L);
        product.updateReview(15, 4.6f);

        return product;
    }
}
