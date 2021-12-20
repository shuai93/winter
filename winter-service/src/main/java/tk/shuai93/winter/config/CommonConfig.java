package tk.shuai93.winter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.shuai93.winter.common.utils.SnowFlake;

/**
 * @Author: gang.chen
 * @CreateTime: 2021-01-20 16:40
 * @Description: description
 **/
@Configuration
public class CommonConfig {

    @Bean
    public SnowFlake snowFlake() {
        var snowflake = new SnowFlake(1, 20);
        return snowflake;
    }

    @Bean
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();
        return mapper;
    }
}
