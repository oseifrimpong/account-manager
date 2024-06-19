package com.acmebank.account_manager.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response<T> {
    private Integer status;
    private String message;
    private T data;

    public Response(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
