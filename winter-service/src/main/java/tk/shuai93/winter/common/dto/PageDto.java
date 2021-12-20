package tk.shuai93.winter.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description PageDto
 * @Author shuai.yang1
 * @Date 2021-12-08 17:29:16
 */
@Data
public class PageDto {

    /**
     * 页数
     */
    @JsonProperty("page_num")
    private Integer pageNum = 1;

    /**
     * 每页大小
     */
    @JsonProperty("page_size")
    private Integer pageSize = 10;
}