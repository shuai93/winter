package tk.shuai93.winter.service;


import org.springframework.stereotype.Service;

/**
 * @Author 杨帅
 * @Date 2021/6/19 上午8:58
 * @Version 1.0
 */
@Service
public class AppServiceImp implements AppService {
    @Override
    public String getApp(String name) {
        System.out.println("Get app method");
        return name;
    }

    @Override
    public String updateApp() {
        System.out.println("Update app method");

        return null;
    }

    @Override
    public String checkApp() {
        System.out.println("Check app method");
        return null;
    }
}
