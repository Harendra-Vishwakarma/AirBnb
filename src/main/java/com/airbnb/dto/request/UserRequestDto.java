package com.airbnb.dto.request;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDto {

    private String name;
    private String email;
    private String phone;
    private String password;


}
