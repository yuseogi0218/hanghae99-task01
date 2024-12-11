package hanghae99.reboot.review.service;

import hanghae99.reboot.review.dto.request.CreateProductReviewRequest;
import hanghae99.reboot.review.dto.response.GetProductReviewsResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ReviewService {

    public GetProductReviewsResponse getProductReviews(Long productId, Integer size) {
        return null;
    }

    public void createProductReview(Long productId, MultipartFile file, CreateProductReviewRequest request) {
    }
}
