package club.kwcoder.report.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 向 redis 中存入字符串
     * @param k key
     * @param v value
     * @return 返回是否存入成功
     */
    public boolean setString(String k, String v) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        try {
            ops.set(k, v);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 向 redis 中存入带有有效期的字符串
     * @param k key
     * @param v value
     * @param l 时间长度，数字
     * @param timeUnit 时间单位
     * @return 返回是否存入成功
     */
    public boolean setString(String k, String v, long l, TimeUnit timeUnit) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        try {
            ops.set(k, v, l, timeUnit);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从 redis 中取出指定的值
     * @param k key
     * @return 返回结果，若为 null，则 redis 中不含有该项
     */
    public String getString(String k) {
        Object o = redisTemplate.opsForValue().get(k);
        return o == null ? null : o.toString();
    }


    /**
     * 从 redis 中删除指定的值
     * @param k key
     * @return 返回结果，true 为删除成功，false 删除失败，null 表示 redis 中不含有该项
     */
    public Boolean del(String k) {
        return redisTemplate.delete(k);
    }

    /**
     * 从 redis 中获取指定命名空间下所有的 key
     * @return 返回 所有 key 的集合
     */
    public Set<String> getAllKeys(String preKeys) {
        return redisTemplate.keys(preKeys + "*");
    }


}
