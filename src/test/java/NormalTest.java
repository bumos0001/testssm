import org.example.ssmtest.SsmtestApplication;
import org.example.ssmtest.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = SsmtestApplication.class)
public class NormalTest {
    @Resource
    private UserService userService;

    @Test
    public void test() {


    }

}
