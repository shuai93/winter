package tk.shuai93.winter.common.enums.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;

/**
 * @Author 杨帅
 * @Date 2021/12/18 20:52
 * @Version 1.0
 */
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        return objectMapper;
    }
}
