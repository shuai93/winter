package tk.shuai93.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


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

    @Value("${mail.user:null}")
    String mailUser;


    @GetMapping("/")
    public String sayHello() {
        log.info("接口为 " + "hello world");

        return "hello world " + mailUser;
    }


}

