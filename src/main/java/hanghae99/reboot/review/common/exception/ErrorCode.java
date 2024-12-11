package hanghae99.reboot.review.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String getCode();
    HttpStatus getHttpStatus();
    String getMessage();
}
