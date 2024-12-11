package hanghae99.reboot.review.product.dto.response;

import java.time.LocalDateTime;

public record GetReviewResponse(
        Long id,
        Long userId,
        Integer score,
        String content,
        String imageUrl,
        LocalDateTime createdAt
) { }
