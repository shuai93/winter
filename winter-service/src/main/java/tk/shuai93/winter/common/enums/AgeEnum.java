package tk.shuai93.winter.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.Arrays;

/**
 * @Author 杨帅
 * @Date 2021/12/18 13:45
 * @Version 1.0
 */
@Getter
public enum AgeEnum implements IEnum<Integer> {
    ONE(1, "一岁"),
    TWO(2, "二岁"),
    THREE(3, "三岁");

    private final int value;
    private final String desc;

    AgeEnum(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }
    public static String getByCode(Integer value) {
        return Arrays.stream(values())
                .filter(t -> t.value == value)
                .findFirst().get().getDesc();
    }
    @Override
    public Integer getValue() {
        return value;
    }
}