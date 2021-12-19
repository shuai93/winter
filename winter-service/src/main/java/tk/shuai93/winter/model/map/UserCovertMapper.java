package tk.shuai93.winter.model.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import tk.shuai93.winter.common.enums.AgeEnum;
import tk.shuai93.winter.entity.User;
import tk.shuai93.winter.model.vo.UserVo;

/**
 * @Author 杨帅
 * @Date 2021/12/18 21:55
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface UserCovertMapper {
    @Mappings({
            @Mapping(source = "age", target = "ageCode"),
            @Mapping(source = "age", target = "ageDesc", qualifiedByName="covertEnum" ),
    })
    UserVo toVo(User user);

    @Named("covertEnum")
    default String convertEnum(Integer code) {
        return AgeEnum.getByCode(code);
    }
}
