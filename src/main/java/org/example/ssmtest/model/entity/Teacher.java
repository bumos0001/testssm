package org.example.ssmtest.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Teacher {
    private int id;
    private String name;

    private List<Course> courses;          // 一對多
}
