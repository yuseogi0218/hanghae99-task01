package hanghae99.reboot.review.product.unit.repository;

import hanghae99.reboot.review.common.RepositoryUnitTest;
import hanghae99.reboot.review.product.domain.Review;
import hanghae99.reboot.review.product.dto.GetProductReviewInfoDTO;
import hanghae99.reboot.review.product.dto.GetProductReviewInfoDTOBuilder;
import hanghae99.reboot.review.product.dto.response.GetReviewResponse;
import hanghae99.reboot.review.product.dto.response.GetReviewResponseBuilder;
import hanghae99.reboot.review.product.repository.ReviewRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories(basePackageClasses = ReviewRepository.class)
public class ReviewRepositoryUnitTest extends RepositoryUnitTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void findOrderByCreatedAtDesc() {
        // given
        Long productId = 1L;
        Integer cursor = 3;
        Pageable pageable = PageRequest.of(0, 2);

        GetReviewResponse review1 = GetReviewResponseBuilder.build1();
        GetReviewResponse review2 = GetReviewResponseBuilder.build2();
        List<GetReviewResponse> expectedReviews = List.of(review2, review1);

        // when
        List<GetReviewResponse> reviews = reviewRepository.findOrderByCreatedAtDesc(productId, cursor, pageable);

        // then
        Assertions.assertThat(reviews).isEqualTo(expectedReviews);
    }

    @Test
    public void findTopByProductIdAndUserId_존재_O() {
        // given
        Long productId = 1L;
        Long userId = 1L;

        // when
        Optional<Review> optionalReview = reviewRepository.findTopByProductIdAndUserId(productId, userId);

        // then
        Assertions.assertThat(optionalReview.isPresent()).isTrue();

    }

    @Test
    public void findTopByProductIdAndUserId_존재_X() {
        // given
        Long productId = 1L;
        Long userId = 0L;

        // when
        Optional<Review> optionalReview = reviewRepository.findTopByProductIdAndUserId(productId, userId);

        // then
        Assertions.assertThat(optionalReview.isEmpty()).isTrue();
    }

    @Test
    public void findReviewInfoByProductId() {
        // given
        Long productId = 1L;

        GetProductReviewInfoDTO expectedDTO = GetProductReviewInfoDTOBuilder.build();

        // when
        GetProductReviewInfoDTO actualDTO = reviewRepository.findReviewInfoByProductId(productId);

        // then
        Assertions.assertThat(actualDTO).isEqualTo(expectedDTO);
    }

}
