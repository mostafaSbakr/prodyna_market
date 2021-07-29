package com.prodyna.prodynamarket.dtos;

import lombok.Data;

@Data
public class UserDto {
    private int userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String dateOfBirth;
    private String phone;
    private String address;
    private String email;
    private String password;
    private String creditCard;
}
