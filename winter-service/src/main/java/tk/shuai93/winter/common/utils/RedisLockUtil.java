package tk.shuai93.winter.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author gang.chen
 * @date 2020/7/16 11:50 PM
 * @description description for this class
 */
@Slf4j
@Component
public class RedisLockUtil {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public boolean lock(String lockKey) {
		return lock(lockKey, 20);
	}

	public boolean lock(String lockKey, Integer expiredSecond) {
		var result =
			stringRedisTemplate.execute(
				(RedisCallback<Boolean>) connection -> connection.set(
					lockKey.getBytes(),
					lockKey.getBytes(),
					Expiration.from(expiredSecond, TimeUnit.SECONDS),
					RedisStringCommands.SetOption.SET_IF_ABSENT));
		log.info("基础服务- 加锁:{}, 锁状态:{}", lockKey, result);
		return result;
	}

	public boolean unlock(String lockKey) {
		var result = stringRedisTemplate.delete(lockKey);
		log.info("基础服务- 解锁:{}, 锁状态:{}", lockKey, result);
		return result;
	}

	/**
	 * 获取自旋锁
	 *
	 * @param lockKey       key
	 * @param expiredSecond 过期时间
	 * @param sleepTime     自旋间隔时间
	 * @param overtime      超时时间
	 * @return
	 */
	public boolean spinLock(String lockKey, Integer expiredSecond, long sleepTime, long overtime, String method) {

		long timeOut = System.currentTimeMillis() + overtime;
		try {
			while (true) {
				long now = System.currentTimeMillis();
				if (timeOut < now) {
					break;
				}
				boolean isLock = this.lock(lockKey, expiredSecond);
				if (isLock) {
					log.info("【供应链金融】获取锁成功 lockKey={} method={}", lockKey, method);
					return true;
				}
				Thread.sleep(sleepTime);
			}
		} catch (Exception ex) {
			log.error("获取自旋锁异常 lockKey={} ex={}", lockKey, ex);
		}
		log.info("获取自旋锁超时 lockKey={}", lockKey);
		return false;
	}

}
