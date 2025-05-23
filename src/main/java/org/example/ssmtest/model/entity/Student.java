package org.example.ssmtest.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student {
    private int id;
    private String name;
    private String email;

    private Address address;                // 一對一
    private List<Course> courses;          // 多對多
}
