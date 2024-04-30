package com.dasa.rms.Account.Users;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userType;
    private boolean enabled;
}
