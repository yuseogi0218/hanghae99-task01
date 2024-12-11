package hanghae99.reboot.review.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "Product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "reviewCount", nullable = false)
    private Integer reviewCount;

    @Column(name = "score", nullable = false)
    private Float score;

    public void updateReview(Integer reviewCount, Float score) {
        this.reviewCount = reviewCount;
        this.score = score;
    }

    public Product(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(reviewCount, product.reviewCount) && Objects.equals(score, product.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reviewCount, score);
    }
}
