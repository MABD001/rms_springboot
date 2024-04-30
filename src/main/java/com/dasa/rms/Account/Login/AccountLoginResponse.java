package com.dasa.rms.Account.Login;


import com.dasa.rms.Account.Users.Users;
import lombok.Data;

@Data
public class AccountLoginResponse {
    private String jwt;
    private Users userId;
    private String name;
    private String email;
}
