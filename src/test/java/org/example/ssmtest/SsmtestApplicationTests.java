package org.example.ssmtest;

import org.example.ssmtest.model.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SsmtestApplicationTests {
    @Resource
    private List<Student> studentList;

    @Test
    void contextLoads() {
        System.out.println(studentList);
    }

}
