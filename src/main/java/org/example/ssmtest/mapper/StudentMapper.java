package org.example.ssmtest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.ssmtest.model.entity.Student;
import org.example.ssmtest.model.entity.Teacher;

import java.util.List;

@Mapper
public interface StudentMapper {
    // 查詢所有學生，並載入該學生的地址資訊（address）
    List<Student> queryAllStudentAndAddress();
    // 查詢某位老師，並載入他所教的所有課程
    List<Teacher> queryAllTeacherAndCourse();
    // 查詢某位學生，並列出他所選的所有課程與授課老師姓名
    List<Student> queryAllStudentAndCourseAndTeacher();

}
