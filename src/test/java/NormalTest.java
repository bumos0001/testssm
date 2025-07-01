import org.example.ssmtest.SsmtestApplication;
import org.example.ssmtest.mapper.UserMapper;
import org.example.ssmtest.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = SsmtestApplication.class)
public class NormalTest {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserMapper userMapper;
    @Test
    public void test() {
        stringRedisTemplate.opsForValue().set("QQ","AA",5, TimeUnit.MINUTES);
//        User user = userMapper.selectById(1);
//        System.out.println(user);


    }
}
