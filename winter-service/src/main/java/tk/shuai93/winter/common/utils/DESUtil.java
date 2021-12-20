package tk.shuai93.winter.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Author: gang.chen
 * @CreateTime: 2021-01-26 16:17
 * @Description: description
 **/
@Slf4j
public class DESUtil {

	private static final String SECRET_KEY = "sdfl23kjlsdf23kljdslfak2lxaslkf23";

	public static String decrypt(String data) throws Exception {
		byte[] bytes = Coder.decryptBASE64(data);
		return new String(DES.decrypt(bytes, SECRET_KEY.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
	}

	public static String encrypt(String data) throws Exception {
		byte[] bytes = DES.encrypt(data.getBytes(StandardCharsets.UTF_8), SECRET_KEY.getBytes(StandardCharsets.UTF_8));
		return Coder.encryptBASE64(bytes);
	}

	static class Coder {

		static byte[] decryptBASE64(String key) throws Exception {
			return Base64.getDecoder().decode(key);
		}

		static String encryptBASE64(byte[] key) throws Exception {
			return Base64.getEncoder().encodeToString(key);
		}
	}

	static class DES {
		public static final String DESEDE_ECB_PKCS_5_PADDING = "DESede/ECB/PKCS5Padding";
		private static final String Algorithm = "DESede";

		static byte[] encrypt(byte[] data, byte[] key) {
			try {
				DESedeKeySpec dks = new DESedeKeySpec(key);
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
				SecretKey secretKey = keyFactory.generateSecret(dks);
				Cipher cipher = Cipher.getInstance(DESEDE_ECB_PKCS_5_PADDING);
				cipher.init(1, secretKey);
				byte[] encryptedData = cipher.doFinal(data);
				return encryptedData;
			} catch (Exception var7) {
				log.error("DES算法，加密数据出错!");
				return null;
			}
		}

		static byte[] decrypt(byte[] data, byte[] key) {
			try {
				DESedeKeySpec dks = new DESedeKeySpec(key);
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
				SecretKey secretKey = keyFactory.generateSecret(dks);
				Cipher cipher = Cipher.getInstance(DESEDE_ECB_PKCS_5_PADDING);
				cipher.init(2, secretKey);
				byte[] decryptedData = cipher.doFinal(data);
				return decryptedData;
			} catch (Exception var7) {
				log.error("DES算法，解密出错。");
				return null;
			}
		}
	}

}
