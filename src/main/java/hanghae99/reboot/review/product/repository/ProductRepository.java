package hanghae99.reboot.review.product.repository;

import hanghae99.reboot.review.product.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
