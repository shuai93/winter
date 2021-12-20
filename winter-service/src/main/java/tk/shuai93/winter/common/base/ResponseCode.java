package tk.shuai93.winter.common.base;

import com.baomidou.mybatisplus.core.toolkit.Assert;

import java.util.Arrays;

/**
 * @Description ResponseCode
 * @Author shuai.yang1
 * @Date 2021-12-08 14:22:24
 */
public enum ResponseCode {
    SUCCESS("200000", "成功"),

    /**
     * 通用错误
     */
    INTERNAL_SERVER_ERROR("500100", "系统服务异常"),
    VALIDATOR_ERROR("500110", "参数校验异常"),
    SERIALIZATION_ERROR("500120", "对象序列化失败"),
    DATE_IS_EMPTY("500130", "日期不能为空"),
    DATE_CONVERT_ERROR("500131", "日期转换出错"),
    OTHER_SERVER_ERROR("500200", "其它系统服务异常"),

    /**
     * CMS 管理类异常
     */
    CMS_MISS_ERROR("400100", "获取资源失败"),
    CMS_CREATE_ERROR("400101", "创建资源失败"),
    CMS_TASK_ERROR("400102", "处理任务失败"),

    /**
     * 运营管理错误
     */

    OPERATE_MISS_ERROR("400100", "运营操作出错"),
    OPERATE_JOB_ERROR("400101", "运营JOB出错"),
    OPERATE_TASK_ERROR("400102", "运营任务出错"),


    /**
     * 接口请求错误
     */

    KUWO_ALBUM_GET_ERROR("400200", "运营操作出错"),
    KUWO_MUSIC_GET_ERROR("400201", "运营JOB出错"),
    KUWO_ERROR("400202", "运营任务出错"),

    ;

    public final String code;
    public final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseCode getByCode(String code) {
        var result = Arrays.stream(values())
                .filter(t -> t.code.equals(code))
                .findFirst();
        Assert.isTrue(result.isPresent(), "ResponseCode cannot be resolved for value: " + code);
        return result.get();
    }

    @Override
    public String toString() {
        return this.message;
    }
}
