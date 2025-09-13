package me.jjye.scheduler_server.common.exception;

import me.jjye.scheduler_server.common.constant.ErrorCode;

/**
 * 애플리케이션 전역에서 사용되는 사용자 정의 예외 클래스
 *
 * <pre>
 * [기능 요약]
 * - ErrorCode 객체를 기반으로 예외 발생
 * - 예외 메시지, HTTP 상태코드, 에러 코드 식별자 등 전달 가능
 *
 * [사용 예시]
 * throw new CustomException(ErrorCode.INVALID_PARAMETER);
 * </pre>
 *
 * @author jjye
 * @since 2025-09-12
 */
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CustomException(ErrorCode errorCode, Exception e) {
        super(errorCode.getMessage(), e);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
