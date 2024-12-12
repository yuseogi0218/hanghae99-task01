package hanghae99.reboot.review.product.api;

import hanghae99.reboot.review.product.dto.request.CreateProductReviewRequest;
import hanghae99.reboot.review.product.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RequiredArgsConstructor
@RequestMapping("/products/{productId}/reviews")
@RestController
public class ReviewAPI {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<?> getProductReviews(
            @PathVariable("productId") Long productId,
            @RequestParam(value = "cursor", required = false) Integer cursor,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    ) {
        return ResponseEntity.ok(reviewService.getProductReviews(productId, cursor, size));
    }

    @PostMapping
    public ResponseEntity<?> createProductReview(
            @PathVariable("productId") Long productId,
            @RequestPart(required = false) MultipartFile file,
            @RequestPart("review") @Valid CreateProductReviewRequest request
    ) {
        reviewService.createProductReview(productId, file, request);
        reviewService.updateProductReviewInfo(productId);
        return ResponseEntity.ok().build();
    }
}
