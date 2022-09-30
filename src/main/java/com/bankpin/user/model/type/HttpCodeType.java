package com.bankpin.user.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum HttpCodeType
{
    OK(200, "클라이언트의 요청을 서버가 정상적으로 처리했다."),
    CREATED(201, "클라이언트의 요청을 서버가 정상적으로 처리했고 새로운 리소스가 생겼다."),
    ACCEPTED(202, "클라이언트의 요청은 정상적이나, 서버가 아직 요청을 완료하지 못했다."),
    NON_AUTHORITATIVE_INFORMATION(203, ""),
    NO_CONTENT(204, "요청에 대해서 보내줄 수 있는 콘텐츠가 없지만, 헤더는 의미있을 수 있습니다."),
    RESET_CONTENT(205, ""),
    PARTIAL_CONTENT(206, ""),
    MULTI_STATUS(207, ""),
    ALREADY_REPORTED(208, ""),
    IM_USED(226, ""),
    MULTIPLE_CHOICE(300, ""),
    MOVED_PERMANENTLY(301, ""),
    FOUND(302, ""),
    SEE_OTHER(303, ""),
    NOT_MODIFIED(304, ""),
    USE_PROXY(305, ""),
    UNUSED(306, ""),
    TEMPORARY_REDIRECT(307, ""),
    PERMANENT_REDIRECT(308, ""),
    BAD_REQUEST(400, "클라이언트의 요청이 유효하지 않아 더 이상 작업을 진행하지 않는 경우."),
    UNAUTHORIZED(401, "클라이언트가 권한이 없기 때문에 작업을 진행할 수 없는 경우."),
    PAYMENT_REQUIRED(402, ""),
    FORBIDDEN(403, "클라이언트가 권한이 없기 때문에 작업을 진행할 수 없는 경우."),
    NOT_FOUND(404, "클라이언트가 요청한 자원이 존재하지 않다."),
    METHOD_NOT_ALLOWED(405, "클라이언트의 요청이 허용되지 않는 메소드인 경우."),
    NOT_ACCEPTABLE(406, ""),
    PROXY_AUTHENTICATION(407, ""),
    REQUEST_TIMEOUT(408, ""),
    CONFLICT(409, "클라이언트의 요청이 서버의 상태와 충돌이 발생한 경우."),
    GONE(410, ""),
    LENGTH_REQUIRED(411, ""),
    PRECONDITION_FAILED(412, ""),
    PAYLOAD_TOO(413, ""),
    URI_TOO_LONG(414, ""),
    UNSUPPORTED_MEDIA(415, ""),
    REQUESTED_RANGE(416, ""),
    EXPECTATION_FAILED(417, ""),
    MISDIRECTED_REQUEST(421, ""),
    UNPROCESSABLE_ENTITY(422, ""),
    LOCKED(423, ""),
    FAILED_DEPENDENCY(424, ""),
    UPGRADE_REQUIRED(426, ""),
    PRECONDITION_REQUIRED(428, ""),
    TOO_MANY(429, "클라이언트가 일정 시간 동안 너무 많은 요청을 보낸 경우."),
    INTERNAL_SERVER_ERROR(500, ""),
    NOT_IMPLEMENTED(501, "서버가 요청을 이행하는 데 필요한 기능을 지원하지 않음을 나타냅니다."),
    BAD_GATEWAY(502, "서버가 게이트웨이로부터 잘못된 응답을 수신했음을 의미합니다."),
    SERVICE_UNAVAILABLE(503, "서버가 요청을 처리할 준비가 되지 않았습니다."),
    GATEWAY_TIMEOUT(504, "웹페이지를 로드하거나 브라우저에서 다른 요청을 채우려는 동안 한 서버가 액세스하고 있는 다른 서버에서 적시에 응답을 받지 못했음을 의미합니다."),
    HTTP_VERSION_NOT_SUPPORTED(505, ""),
    VARIANT_ALSO_NEGOTIATES(506, "서버에 내부 구성 오류가 있는 경우 발생합니다."),
    INSUFFICIENT_STORAGE(507, ""),
    LOOP_DETECTED(508, "서버가 요청을 처리하는 동안 무한 루프를 감지한 경우 발생합니다."),
    NOT_EXTENDED(510, "서버가 요청을 이행하려면 요청에 대한 추가 확장이 필요합니다."),
    NETWORK_AUTHENTICATION(511, "상태 코드는 클라이언트가 네트워크 액세스를 얻기 위해 인증할 필요가 있음을 나타냅니다."),
    ;

    private final int code;
    private final String label;

    public static HttpCodeType of(int code) {
        return Arrays.stream(HttpCodeType.values())
                .filter(r -> r.getCode() == code)
                .findAny()
                .orElse(OK);
    }
}
