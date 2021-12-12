package com.github.shuai93.winter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.shuai93.winter.WinterApplication;
import tk.shuai93.winter.service.AppService;
import tk.shuai93.winter.service.UserService;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @Author 杨帅
 * @Date 2021/6/18 下午11:37
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WinterApplication.class)
public class UserTest {

    @SpyBean
    private UserService userService;

    @MockBean
    private AppService appService;


    @Test
    public void spyUser() {
        String user = "bob";
        // SpyBean 相当于 Autowired
        Assert.assertEquals(user, userService.getUser(user));
    }


    @Test
    public void spyUser1() {
        String user = "bob";
        // 运行方法并覆盖返回值
        PowerMockito.when(userService.checkUser()).thenReturn("ok");
        assertThat(userService.updateUser(), equalTo(user));
    }

    @Test
    public void spyUser2() {
        String user = "bob";
        // 不运行方法直接设置返回值
        PowerMockito.doReturn("ok").when(userService).checkUser();
        assertThat(userService.updateUser(), equalTo(user));
    }

    @Test
    public void mockApp1() {
        String app = "chrome";
        // MockBean 默认返回值 为null
        assertThat(appService.getApp(app), equalTo(null));
    }

    @Test
    public void mockApp2() {
        String app = "chrome";
        // MockBean 默认返回值 修改为 chrome
        PowerMockito.when(appService.getApp(Mockito.any())).thenReturn(app);
        assertThat(appService.getApp(app), equalTo(app));
    }
}
