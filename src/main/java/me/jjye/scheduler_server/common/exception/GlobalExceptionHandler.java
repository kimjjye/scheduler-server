package me.jjye.scheduler_server.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import me.jjye.scheduler_server.common.constant.ErrorCode;
import me.jjye.scheduler_server.common.response.CommonResponse;
import me.jjye.scheduler_server.common.response.CommonResponseWriter;

/**
 * 애플리케이션 전역에서 발생하는 예외를 처리하는 핸들러 클래스
 *
 * <pre>
 * [기능 요약]
 * - Exception 발생 시 CommonResponseWriter를 통해 일관된 JSON 응답 반환
 * - 예외 유형별로 분기 처리 가능 (예: 일반 Exception, RuntimeException 등)
 * - CommonResponseWriter.error(...) 포맷을 유지하며 ResponseEntity로 응답
 *
 * [사용 예시]
 * 예외 발생 → GlobalExceptionHandler → CommonResponseWriter.error(errorCode)
 * </pre>
 *
 * @author jjye
 * @since 2025-09-13
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CommonResponse<Void>> handleCustom(CustomException ex) {
        ErrorCode code = ex.getErrorCode();
        return CommonResponseWriter.error(code);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Void>> handleGeneric(Exception ex) {
        return CommonResponseWriter.error(ErrorCode.INTERNAL_ERROR);
    }
}
