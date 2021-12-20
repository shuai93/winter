package tk.shuai93.winter.common.base;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * @Description ResponseResultBody
 * @Author shuai.yang1
 * @Date 2021-12-08 14:52:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ResponseBody
public @interface ResponseResultBody {

}
