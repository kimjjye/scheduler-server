package me.jjye.scheduler_server.common.util;

import java.io.IOException;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON 직렬화 및 역직렬화를 위한 공통 유틸 클래스
 *
 * <pre>
 * [기능 요약]
 * - 객체 → JSON 문자열 변환
 * - JSON 문자열 → 객체 변환
 * - JSON 트리 구조 파싱 (JsonNode)
 *
 * [사용 예시]
 * jsonUtil.toJson(myObject);
 * jsonUtil.fromJson(jsonString, new TypeReference<>() {});
 *
 * </pre>
 *
 * @author jjye
 * @since 2025-09-12
 */
@Component
public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T fromJson(String json, TypeReference<T> typeRef) throws IOException {
        return mapper.readValue(json, typeRef);
    }

    public static String toJson(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    public static JsonNode readTree(String json) throws JsonProcessingException {
        return mapper.readTree(json);
    }
}
