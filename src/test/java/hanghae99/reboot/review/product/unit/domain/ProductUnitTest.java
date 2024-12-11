package hanghae99.reboot.review.product.unit.domain;

import hanghae99.reboot.review.product.domain.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductUnitTest {

    @Test
    public void updateReviewInfo() {
        // given
        Product product = new Product(1L);
        Integer reviewCount = 10;
        Float score = 4.5f;

        // when
        product.updateReviewInfo(reviewCount, score);

        // then
        Assertions.assertThat(product.getReviewCount()).isEqualTo(reviewCount);
        Assertions.assertThat(product.getScore()).isEqualTo(score);
    }

    @Test
    public void constructorWithId() {
        // given
        Long id = 1L;

        // when
        Product product = new Product(id);

        // then
        Assertions.assertThat(product.getId()).isEqualTo(id);
    }
}
