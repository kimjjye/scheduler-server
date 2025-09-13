package me.jjye.scheduler_server.common.response;

import java.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import me.jjye.scheduler_server.common.constant.ErrorCode;
import me.jjye.scheduler_server.common.util.JsonUtils;

/**
 * 공통 API 응답 포맷(CommonResponse)을 생성하고 반환하는 유틸리티 클래스
 *
 * <pre>
 * [기능 요약]
 * - 성공/실패 응답을 CommonResponse 구조로 생성
 * - 컨트롤러에서는 ResponseEntity<CommonResponse> 반환
 * - 인터셉터, 필터 등에서는 HttpServletResponse에 직접 JSON 작성
 * - 응답 포맷: success, data, message, code, timestamp 포함
 *
 * [사용 예시]
 * // 컨트롤러
 * CommonResponseWriter.success(dto);
 * CommonResponseWriter.error(ErrorCode.VERSION_NOT_SUPPORTED);
 *
 * // 인터셉터
 * CommonResponseWriter.success(response, dto);
 * CommonResponseWriter.error(response, ErrorCode.VERSION_NOT_SUPPORTED);
 *
 * [JSON 응답 예시]
 * {
 *   success: false,
 *   data: null
 *   code: "INVALID_PARAMETER",
 *   message: "잘못된 요청입니다.",
 *   timestamp: "2025-07-14T10:42:00"
 * }
 * </pre>
 *
 * @author jjye
 * @since 2025-09-13
 */
@Component
public class CommonResponseWriter {

    public static <T> ResponseEntity<CommonResponse<T>> success(T data) {
        return ResponseEntity.ok(CommonResponse.success(data));
    }

    public static <T> ResponseEntity<CommonResponse<T>> error(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus()).body(CommonResponse.error(errorCode));
    }

    public static <T> void success(HttpServletResponse response, T data) throws IOException {
        CommonResponse<T> apiResponse = CommonResponse.success(data);
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JsonUtils.toJson(apiResponse));
    }

    public static void error(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        CommonResponse<Void> apiResponse = CommonResponse.error(errorCode);
        response.setStatus(errorCode.getHttpStatus());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JsonUtils.toJson(apiResponse));
    }
}