package org.example.ssmtest.model.request;

import lombok.Data;

@Data
public class UserBalanceUpdateRequest {
    private Integer id;
    private Integer amount;
}
