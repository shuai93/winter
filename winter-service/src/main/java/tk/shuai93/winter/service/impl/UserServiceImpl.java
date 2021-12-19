package tk.shuai93.winter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import tk.shuai93.winter.entity.User;
import tk.shuai93.winter.mapper.UserMapper;
import tk.shuai93.winter.model.map.UserCovertMapper;
import tk.shuai93.winter.model.vo.UserVo;
import tk.shuai93.winter.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shuai93
 * @since 2021-11-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    final UserMapper userMapper;
    final UserCovertMapper mapper;

    public UserServiceImpl(UserMapper userMapper, UserCovertMapper mapper) {
        this.userMapper = userMapper;
        this.mapper = mapper;
    }

    @Override
    public UserVo getUser(String name) {
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.eq("name", name);
        List<User> users = userMapper.selectList(query);
        if (!users.isEmpty()) {
            User user = users.get(0);
            return mapper.toVo(user);
        }

        return null;
    }

    @Override
    public String updateUser() {
        String res = checkUser();
        if (res.equals("ok")) {
            System.out.println("Update check method");
            return "bob";
        }
        System.out.println("Update method");
        return "andy";
    }

    @Override
    public String checkUser() {
        System.out.println("check method");
        return "ok";
    }
}
