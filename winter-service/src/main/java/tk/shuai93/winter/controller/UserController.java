package tk.shuai93.winter.controller;


import tk.shuai93.winter.entity.User;
import tk.shuai93.winter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shuai93
 * @since 2021-11-28
 */
@RestController
@Slf4j
public class UserController {

    @Value("${mail.user: null}")
    String mailUser;

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String sayHello() {
        log.info("接口为" + "hello world");

        return "hello world" + mailUser;
    }

    @GetMapping("/user")
    public User getUser() {
        log.info("接口为" + "user");
        return userService.getUser("Jack");
    }

    @GetMapping("/sleep")
    public User sleep() throws InterruptedException {
        Random r = new Random();
        int n3 = Math.abs(r.nextInt() % 1000);
        Thread.sleep(1000 + n3);
        log.info("接口时间为" + n3);
        return userService.getUser("Jack");
    }
}

