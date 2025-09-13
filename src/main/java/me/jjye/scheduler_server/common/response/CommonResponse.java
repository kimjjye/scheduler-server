package me.jjye.scheduler_server.common.response;

import java.time.LocalDateTime;
import me.jjye.scheduler_server.common.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * API 응답의 공통 포맷을 정의하는 제네릭 클래스
 *
 * <pre>
 * [기능 요약]
 * - 요청 성공 여부(success) 포함
 * - 성공/실패에 따라 data 또는 error 정보 포함
 * - 응답 생성 시점의 타임스탬프 포함
 *
 * [사용 예시]
 * CommonResponse.success(userDto);
 * CommonResponse.error(ErrorCode.INVALID_REQUEST);
 *
 * [JSON 응답 예시]
 * {
 *   success: boolean,  // 요청 성공 여부
 *   data: T,           // 성공 시 전달할 데이터
 *   message: String,   // 실패 또는 설명 메시지
 *   code: String,      // 에러 코드 (ErrorCode enum 사용)
 *   timestamp: LocalDateTime  // 응답 생성 시간
 * }
 * </pre>
 *
 * @author jjye
 * @since 2025-09-13
 */
@Getter
@AllArgsConstructor
public class CommonResponse<T> {
    private boolean success;
    private T data;
    private String message;
    private String code;
    private LocalDateTime timestamp;

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(true, data, null, null, LocalDateTime.now());
    }

    public static <T> CommonResponse<T> error(ErrorCode errorCode) {
        return new CommonResponse<>(false, null, errorCode.getMessage(), errorCode.name(), LocalDateTime.now());
    }

}
