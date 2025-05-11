package org.example.ssmtest.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
    private Integer id;
    private Integer studentId;
    private String city;
    private String street;
}
