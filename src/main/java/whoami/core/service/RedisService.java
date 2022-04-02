package whoami.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate redisTemplate;

    // NOTE : 키-벨류 설정
    public void setValues(String token, String userId){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        System.out.println("setValues : " + token + userId);
        values.set(token, userId, Duration.ofMinutes(3));  // 3분 뒤 메모리에서 삭제된다.
    }

    // NOTE : 키값으로 벨류 가져오기
    public String getValues(String token){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        System.out.println("getValue: " + token);
        return values.get(token);
    }

    // NOTE : 키-벨류 삭제
    public void delValues(String token) {
        System.out.println("delvalues : "+ token);
        redisTemplate.delete(token);
    }
}
