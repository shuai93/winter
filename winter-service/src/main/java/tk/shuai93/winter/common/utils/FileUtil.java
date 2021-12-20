package tk.shuai93.winter.common.utils;

import com.google.common.io.CharStreams;
import com.ke.scf.common.exception.ResponseCode;
import com.ke.scf.common.exception.ScfBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Archer
 * @date 2021/2/10 4:40 AM
 * @description description for this class
 */
@Slf4j
public class FileUtil {

	public static MultipartFile createMultipartFile(String fieldName) {
		var file = new File(fieldName);
		try (var fileInputStream = new FileInputStream(file)) {
			var multipartFile =
				new MockMultipartFile(
					file.getName(),
					file.getName(),
					ContentType.APPLICATION_OCTET_STREAM.toString(),
					fileInputStream);
			return multipartFile;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public static void writeFile(String filePath, byte[] content) {
		try {
			var path = Paths.get(filePath);
			Files.write(path, content);
		} catch (IOException e) {
			throw new ScfBusinessException(ResponseCode.WRITE_FILE_ERROR);
		}
	}

	public static void writeFile(String filePath, String content, String charset) {
		try {
			var path = Paths.get(filePath);
			Files.write(path, content.getBytes(charset), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			throw new ScfBusinessException(ResponseCode.WRITE_FILE_ERROR);
		}
	}

	public static String loadResourceFile(String fileName, String charset) {
		var classPathResource = new ClassPathResource(fileName);
		try (var inputStream = classPathResource.getInputStream()) {
			var orderContent = CharStreams.toString(new InputStreamReader(inputStream, charset));
			return orderContent;
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return "";
	}
}
