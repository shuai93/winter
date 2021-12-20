package tk.shuai93.winter.common.base;

import lombok.Data;

/**
 * @Description
 * @Author shuai.yang1
 * @Date 2021-12-08 14:41:50
 */
@Data
public class BaseException extends RuntimeException{

    private String code;
    private String message;

    public BaseException(String code) {
        this.code = code;
    }
    public BaseException(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public BaseException(ResponseCode response) {
        this.code = response.code;
        this.message = response.message;

    }
}