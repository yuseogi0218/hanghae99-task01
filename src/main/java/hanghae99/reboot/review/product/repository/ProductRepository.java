package hanghae99.reboot.review.product.repository;

import hanghae99.reboot.review.product.domain.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findTopById(Long id);

    @Query(value = "select * From Product p where p.id = :id limit 1 for update", nativeQuery = true)
    Optional<Product> findTopByIdForUpdate(Long id);
}
