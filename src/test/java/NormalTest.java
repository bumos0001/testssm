import org.example.ssmtest.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class NormalTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void test() {
        
    }
}
