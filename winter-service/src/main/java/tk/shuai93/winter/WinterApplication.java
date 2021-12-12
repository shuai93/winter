package tk.shuai93.winter;


import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@EnableApolloConfig
@MapperScan("tk.shuai93.winter.mapper")
public class WinterApplication {
    public static void main(String[] args) {
        SpringApplication.run(WinterApplication.class, args);
    }

}
