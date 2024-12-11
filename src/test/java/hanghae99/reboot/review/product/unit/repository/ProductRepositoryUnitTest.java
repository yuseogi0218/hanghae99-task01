package hanghae99.reboot.review.product.unit.repository;

import hanghae99.reboot.review.common.RepositoryUnitTest;
import hanghae99.reboot.review.product.domain.Product;
import hanghae99.reboot.review.product.domain.ProductBuilder;
import hanghae99.reboot.review.product.repository.ProductRepository;
import hanghae99.reboot.review.product.repository.ReviewRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories(basePackageClasses = ProductRepository.class)
public class ProductRepositoryUnitTest extends RepositoryUnitTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findTopById_존재_O() {
        // given
        Long productId = 1L;
        Product expectedProduct = ProductBuilder.build();

        // when
        Optional<Product> optionalProduct = productRepository.findTopById(productId);

        // then
        Assertions.assertThat(optionalProduct.isPresent()).isTrue();
        optionalProduct.ifPresent(
                actualProduct -> {
                    Assertions.assertThat(actualProduct).isEqualTo(expectedProduct);
                }
        );
    }
}
