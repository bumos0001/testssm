import org.example.ssmtest.SsmtestApplication;
import org.example.ssmtest.mapper.PermissionMapper;
import org.example.ssmtest.mapper.UserMapper;
import org.example.ssmtest.model.entity.Permission;
import org.example.ssmtest.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = SsmtestApplication.class)
public class NormalTest {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserMapper userMapper;

    @Resource
    private PermissionMapper permissionMapper;
    @Test
    public void test() {
        List<Permission> byRoleId = permissionMapper.findByRoleId(1);
        byRoleId.forEach(System.out::println);

    }
}
