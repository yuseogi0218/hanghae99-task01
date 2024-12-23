package hanghae99.reboot.review.product.service;

import hanghae99.reboot.review.common.exception.CommonErrorCode;
import hanghae99.reboot.review.common.exception.CustomCommonException;
import hanghae99.reboot.review.common.file.FileService;
import hanghae99.reboot.review.common.lock.DistributedLock;
import hanghae99.reboot.review.product.domain.Product;
import hanghae99.reboot.review.product.domain.Review;
import hanghae99.reboot.review.product.dto.GetProductReviewInfoDTO;
import hanghae99.reboot.review.product.dto.request.CreateProductReviewRequest;
import hanghae99.reboot.review.product.dto.response.GetProductReviewsResponse;
import hanghae99.reboot.review.product.dto.response.GetReviewResponse;
import hanghae99.reboot.review.product.exception.ProductErrorCode;
import hanghae99.reboot.review.product.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final ProductService productService;
    private final FileService fileService;

    @Transactional(readOnly = true)
    public GetProductReviewsResponse getProductReviews(Long productId, Integer cursor, Integer size) {
        Product product = productService.getProductById(productId);

        List<GetReviewResponse> reviews = reviewRepository.findOrderByCreatedAtDesc(productId, cursor, PageRequest.of(0, size));

        return GetProductReviewsResponse.from(product, reviews);
    }

    @DistributedLock(key = "#productId + '-' + #request.userId()")
    public void createProductReview(Long productId, MultipartFile file, CreateProductReviewRequest request) {
        productService.getProductById(productId);

        if (reviewRepository.findTopByProductIdAndUserId(productId, request.userId()).isPresent()) {
            throw new CustomCommonException(ProductErrorCode.ALREADY_REVIEWED_PRODUCT);
        }

        String fileUrl = fileService.uploadFile(file);
        Review review = request.toEntity(productId, fileUrl);

        reviewRepository.save(review);
    }

    @DistributedLock(key = "#productId")
    public void updateProductReviewInfo(Long productId) {
        Product product = productService.getProductById(productId);

        GetProductReviewInfoDTO productReviewInfo = reviewRepository.findReviewInfoByProductId(productId);
        product.updateReviewInfo(productReviewInfo.totalCount(), productReviewInfo.score());
    }
}
