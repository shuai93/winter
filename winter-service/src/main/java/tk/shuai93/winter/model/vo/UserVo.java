package tk.shuai93.winter.model.vo;

import lombok.Data;

/**
 * @Author 杨帅
 * @Date 2021/12/18 21:52
 * @Version 1.0
 */
@Data
public class UserVo {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */

    private int ageCode;

    private String ageDesc;

    /**
     * 邮箱
     */
    private String email;


}
