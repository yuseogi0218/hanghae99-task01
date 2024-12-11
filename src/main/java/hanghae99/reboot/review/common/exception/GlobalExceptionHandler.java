package hanghae99.reboot.review.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomCommonException.class)
    public ResponseEntity<?> handleCustomCommonException(CustomCommonException e) {
        return e.toErrorResponse();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        CustomCommonException exception = new CustomCommonException(CommonErrorCode.MISMATCH_PARAMETER_TYPE, e.getName());
        return exception.toErrorResponse();
    }
}
