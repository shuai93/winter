package tk.shuai93.winter.common.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

/**
 * @Description Response
 * @Author shuai.yang1
 * @Date 2021-12-08 14:51:10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("result_code")
    protected String resultCode;

    @JsonProperty("server_time")
    protected long serverTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String message;

    @JsonProperty("display_msg")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String displayMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("debug_msg")
    protected String debugMessage;

    @SerializedName("data")
    private T data;

    public static <T> Response<T> success(T data) {
        return Response.<T>builder()
                .requestId(UUID.randomUUID().toString().replace("-", ""))
                .resultCode(ResponseCode.SUCCESS.code)
                .message(ResponseCode.SUCCESS.message)
                .serverTime(System.currentTimeMillis() / 1000L)
                .data(data)
                .build();
    }

    public static <T> Response<T> success() {
        return Response.<T>builder()
                .requestId(UUID.randomUUID().toString().replace("-", ""))
                .resultCode(ResponseCode.SUCCESS.code)
                .message(ResponseCode.SUCCESS.message)
                .serverTime(System.currentTimeMillis() / 1000L)
                .build();
    }

    public static <T> Response<T> success(String requestId, T data) {
        return Response.<T>builder()
                .requestId(requestId)
                .resultCode(ResponseCode.SUCCESS.code)
                .message(ResponseCode.SUCCESS.message)
                .serverTime(System.currentTimeMillis() / 1000L)
                .data(data)
                .build();
    }

    public static <T> Response<T> failure(T data) {
        return Response.<T>builder()
                .resultCode(ResponseCode.INTERNAL_SERVER_ERROR.code)
                .requestId(UUID.randomUUID().toString().replace("-", ""))
                .message(ResponseCode.INTERNAL_SERVER_ERROR.message)
                .serverTime(System.currentTimeMillis() / 1000L)
                .data(data)
                .build();
    }

    public static <T> Response<T> failure(String code, String message) {
        if (ObjectUtils.isEmpty(message)) {
            message = ResponseCode.INTERNAL_SERVER_ERROR.message;
        }
        return Response.<T>builder()
                .resultCode(code)
                .requestId(UUID.randomUUID().toString().replace("-", ""))
                .message(message)
                .serverTime(System.currentTimeMillis() / 1000L)
                .build();
    }

    public static <T> Response<T> failure(String message) {
        if (ObjectUtils.isEmpty(message)) {
            message = ResponseCode.INTERNAL_SERVER_ERROR.message;
        }
        return Response.<T>builder()
                .resultCode(ResponseCode.INTERNAL_SERVER_ERROR.code)
                .requestId(UUID.randomUUID().toString().replace("-", ""))
                .message(message)
                .serverTime(System.currentTimeMillis() / 1000L)
                .build();
    }


    public static <T> Response<T> failure(ResponseCode repayCode) {
        return Response.<T>builder()
                .resultCode(repayCode.code)
                .requestId(UUID.randomUUID().toString().replace("-", ""))
                .message(repayCode.message)
                .serverTime(System.currentTimeMillis() / 1000L)
                .build();
    }

    public static <T> Response<T> build(ResponseCode repayCode, T data) {
        return Response.<T>builder()
                .resultCode(repayCode.code)
                .requestId(UUID.randomUUID().toString().replace("-", ""))
                .message(repayCode.message)
                .data(data)
                .serverTime(System.currentTimeMillis() / 1000L)
                .build();
    }
}
