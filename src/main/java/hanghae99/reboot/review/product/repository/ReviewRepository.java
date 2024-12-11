package hanghae99.reboot.review.product.repository;

import hanghae99.reboot.review.product.domain.Review;
import hanghae99.reboot.review.product.dto.GetProductReviewInfoDTO;
import hanghae99.reboot.review.product.dto.response.GetReviewResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    @Query("SELECT new hanghae99.reboot.review.product.dto.response.GetReviewResponse(r.id, r.userId, r.score, r.content, r.imageUrl, r.createdAt)" +
            "FROM Review r WHERE r.product.id = :productId ORDER BY r.createdAt DESC")
    List<GetReviewResponse> findOrderByCreatedAtDesc(Long productId, Pageable pageable);

    Optional<Review> findTopByProductIdAndUserId(Long productId, Long userId);

    @Query("SELECT new hanghae99.reboot.review.product.dto.GetProductReviewInfoDTO(COUNT(*), AVG(r.score)) FROM Review r WHERE r.product.id = :productId")
    GetProductReviewInfoDTO findReviewInfoByProductId(Long productId);
}
