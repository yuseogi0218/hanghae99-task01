package hanghae99.reboot.review.common.exception.response;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String errorCode,
        String message
) { }
