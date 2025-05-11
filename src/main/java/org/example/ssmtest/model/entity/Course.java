package org.example.ssmtest.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Course {
    private int id;
    private String name;
    private String description;
    private int teacherId;
    private Teacher teacher;               // 一對多（課程屬於一位老師）
}
