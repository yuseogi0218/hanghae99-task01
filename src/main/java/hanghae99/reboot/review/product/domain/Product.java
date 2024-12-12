package hanghae99.reboot.review.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public void updateReviewInfo(Integer reviewCount, Float score) {
        this.reviewCount = reviewCount;
        this.score = score;
    }

    public Product(Long id) {
        this.id = id;
    }

}
