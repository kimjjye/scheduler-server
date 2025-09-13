package me.jjye.scheduler_server.common.constant;
/**
 * 비즈니스 및 시스템 오류를 식별하는 API 전용 에러 코드 집합
 *
 * <pre>
 * - 각 에러코드는 HTTP 상태 코드와 식별용 코드 ID, 메시지를 포함
 * - 응답 객체(ApiResponse)에 포함되어 클라이언트에서 처리 기준으로 활용됨
 * 
 * C = Client
 * E = Error
 * U = User
 * 
 * </pre>
 *
 * @author jjye
 * @since 2025-09-12
 */
public enum ErrorCode {
    // 400 - Bad Request
    INVALID_PARAMETER(400, "C001", "잘못된 요청입니다."),
    MISSING_REQUIRED_FIELD(400, "C002", "필수 입력값이 누락되었습니다."),
    TYPE_MISMATCH(400, "C003", "파라미터 타입이 올바르지 않습니다."),
    INVALID_ENUM_VALUE(400, "C004", "허용되지 않는 값입니다."),

    // 404 - Not Found
    RESOURCE_NOT_FOUND(404, "E404", "요청한 리소스를 찾을 수 없습니다."),
    USER_NOT_FOUND(404, "U001", "사용자를 찾을 수 없습니다."),
    
    // 409 - Conflict
    DUPLICATE_USER(409, "C409", "이미 존재하는 사용자입니다."),

    // 422 - Unprocessable Entity
    VALIDATION_FAILED(422, "C422", "요청 데이터의 유효성 검증에 실패했습니다."),

    // 500 - Internal Server Error
    INTERNAL_ERROR(500, "E500", "서버 내부 오류가 발생했습니다."),
    
    // 503 - Service Unavailable
    SERVICE_UNAVAILABLE(503, "E503", "서비스를 사용할 수 없습니다. 잠시 후 다시 시도해주세요.");

    private final int httpStatus;     // 외부 프로토콜 응답용
    private final String codeId;      // 내부 식별용
    private final String message;     // 사용자 메시지

    ErrorCode(int httpStatus,String codeId, String message) {
        this.httpStatus = httpStatus;
        this.codeId = codeId;
        this.message = message;
    }

    public int getHttpStatus() { return httpStatus; }
    public String getCodeId() { return codeId; }
    public String getMessage() { return message; }

    @Override
    public String toString() {
        return String.format("[%s] %s (%d)", codeId, message, httpStatus);
    }
}
