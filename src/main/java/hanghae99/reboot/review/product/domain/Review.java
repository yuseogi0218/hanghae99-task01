package hanghae99.reboot.review.product.domain;

import hanghae99.reboot.review.common.entity.CreatedAtEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Review extends CreatedAtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", nullable = false, updatable = false)
    private Product product;

    @Column(name = "userId", nullable = false, updatable = false)
    private Long userId;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "imageUrl")
    private String imageUrl;
}
