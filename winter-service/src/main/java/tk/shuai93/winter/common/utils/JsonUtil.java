package tk.shuai93.winter.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import tk.shuai93.winter.common.base.BaseException;
import tk.shuai93.winter.common.base.ResponseCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gang.chen
 * @date 2020/7/22 12:13 AM
 * @description description for this class
 */
@Slf4j
public class JsonUtil {
	static ObjectMapper objectMapper = new ObjectMapper();

	public static <T> String writeAsString(T t) {
		try {
			return objectMapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			log.error("对象转换失败:{}", t);
		}
		return "";
	}

	public static <T> T readValue(String data, TypeReference<T> clazz) {
		try {
			return objectMapper.readValue(data, clazz);
		} catch (Exception e) {
			log.error("对象转换失败: {}, {} ", data, ExceptionUtils.getStackTrace(e));
			throw new BaseException(ResponseCode.SERIALIZATION_ERROR);
		}
	}

	public static <T> T readValue(String data, Class<T> clazz) {
		try {
			return objectMapper.readValue(data, clazz);
		} catch (Exception e) {
			log.error("对象转换失败: {}, {} ", data, ExceptionUtils.getStackTrace(e));
			throw new BaseException(ResponseCode.SERIALIZATION_ERROR);
		}
	}

	public static <T> List<T> toList(String json, Class<T> clazz) {
		try {
			var listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
			return objectMapper.readValue(json, listType);
		} catch (Exception e) {
			log.error("对象转换失败:{},{}", json, ExceptionUtils.getStackTrace(e));
			throw new BaseException(ResponseCode.SERIALIZATION_ERROR);
		}
	}
}
