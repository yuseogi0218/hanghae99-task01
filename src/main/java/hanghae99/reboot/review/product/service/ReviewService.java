package hanghae99.reboot.review.product.service;

import hanghae99.reboot.review.product.domain.Product;
import hanghae99.reboot.review.product.dto.request.CreateProductReviewRequest;
import hanghae99.reboot.review.product.dto.response.GetProductReviewsResponse;
import hanghae99.reboot.review.product.dto.response.GetReviewResponse;
import hanghae99.reboot.review.product.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final ProductService productService;

    @Transactional(readOnly = true)
    public GetProductReviewsResponse getProductReviews(Long productId, Integer cursor, Integer size) {
        Product product = productService.getProductById(productId);

        List<GetReviewResponse> reviews = reviewRepository.findOrderByCreatedAtDesc(productId, PageRequest.of(0, size));

        return GetProductReviewsResponse.from(product, cursor, reviews);
    }

    public void createProductReview(Long productId, MultipartFile file, CreateProductReviewRequest request) {
    }
}
