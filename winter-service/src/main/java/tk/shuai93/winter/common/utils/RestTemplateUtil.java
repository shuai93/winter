package tk.shuai93.winter.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author: gang.chen
 * @CreateTime: 2020-03-30 17:05
 * @Description: description
 **/
@Slf4j
@Component
public class RestTemplateUtil {
	@Autowired
	RestTemplate restTemplate;

	public <T, F> F post(String url, T req, Class<F> clazz, Map<String, String> map) {
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		var jsonStr = JsonUtil.writeAsString(req);
		HttpEntity<String> formEntity = new HttpEntity<String>(jsonStr, headers);
		log.info("基础服务>>>  " + url);
		log.info("基础服务request >>> " + jsonStr);
		String result = restTemplate.postForObject(url, formEntity, String.class, map);
		log.info("基础服务response >>> " + result);
		return JsonUtil.readValue(result, clazz);
	}

	public <T> T get(String url, Map<String, String> map, Class<T> clazz) {
		log.info("基础服务>>>  " + url);
		log.info("基础服务request >>> " + JsonUtil.writeAsString(map));
		var res = restTemplate.getForEntity(url, clazz, map);
		log.info("基础服务response >>> " + JsonUtil.writeAsString(res));
		return res.getBody();
	}
}
