package tk.shuai93.winter.service;

import tk.shuai93.winter.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import tk.shuai93.winter.model.vo.UserVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shuai93
 * @since 2021-11-28
 */
public interface UserService extends IService<User> {

    UserVo getUser(String name);

    String updateUser();

    String checkUser();


}
