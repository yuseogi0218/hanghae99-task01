package hanghae99.reboot.review.product.exception;

import hanghae99.reboot.review.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ProductErrorCode implements ErrorCode {

    // 400 BAD_REQUEST 잘못된 요청
    ALREADY_REVIEWED_PRODUCT("PRODUCT_400_01", HttpStatus.BAD_REQUEST, "해당 상품에 이미 리뷰를 작성하였습니다."),

    // 404 NOT_FOUND 리소스가 존재하지 않음
    NOT_FOUND_PRODUCT("PRODUCT_404_01", HttpStatus.NOT_FOUND, "해당하는 상품이 존재하지 않습니다.");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
