package hanghae99.reboot.review.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum CommonErrorCode implements ErrorCode {

    // 400 BAD_REQUEST 잘못된 요청
    BAD_REQUEST("COMMON_400_01", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    MISMATCH_PARAMETER_TYPE("COMMON_400_02", HttpStatus.BAD_REQUEST, "%s 파라미터의 타입이 올바르지 않습니다."),
    REQUIRED_PARAMETER("COMMON_400_03", HttpStatus.BAD_REQUEST, "%s 파라미터는 필수 입력값입니다."),

    INVALID_REQUEST_BODY_FIELDS("COMMON_400_04", HttpStatus.BAD_REQUEST, "%s"),

    // 500 INTERNAL SERVER ERROR 서버 에러
    INTERNAL_SERVER_ERROR("COMMON_500_01", HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러 입니다."),
    UNABLE_TO_UPLOAD_FILE("COMMON_500_02", HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드가 불가능합니다.");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
