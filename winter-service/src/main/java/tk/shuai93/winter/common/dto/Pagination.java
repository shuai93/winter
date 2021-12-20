package tk.shuai93.winter.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description Pagination
 * @Author shuai.yang1
 * @Date 2021-12-08 17:26:19
 */
@Data
public class Pagination<T> {

    List<T> list;
    private Long total;
    @JsonProperty("page_num")
    private Long pageNum;
    @JsonProperty("page_size")
    private Long pageSize = 10L;

    public static <T> Pagination<T> empty() {
        var result = new Pagination<T>();
        result.setList(List.of());
        result.setTotal(0L);
        result.setPageNum(1L);
        return result;
    }

    public static <T> Pagination<T> build(Long pageNo, Long pageSize, Long total, List<T> records) {
        var result = new Pagination<T>();
        result.setList(records);
        result.setTotal(total);
        result.setPageSize(pageSize);
        result.setPageNum(pageNo);
        return result;
    }

}
