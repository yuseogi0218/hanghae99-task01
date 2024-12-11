package hanghae99.reboot.review.product.unit.repository;

import hanghae99.reboot.review.common.RepositoryUnitTest;
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

@EnableJpaRepositories(basePackageClasses = ReviewRepository.class)
public class ReviewRepositoryUnitTest extends RepositoryUnitTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void findOrderByCreatedAtDesc() {
        // given
        Long productId = 1L;
        Pageable pageable = PageRequest.of(0, 2);

        GetReviewResponse review1 = GetReviewResponseBuilder.build1();
        GetReviewResponse review2 = GetReviewResponseBuilder.build2();
        List<GetReviewResponse> expectedReviews = List.of(review1, review2);

        // when
        List<GetReviewResponse> reviews = reviewRepository.findOrderByCreatedAtDesc(productId, pageable);

        // then
        Assertions.assertThat(reviews).isEqualTo(expectedReviews);
    }

}
