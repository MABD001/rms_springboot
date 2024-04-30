package com.dasa.rms.Account.Login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountLoginRequest {
    @NotNull(message = "Email Should Not Be Null")
    @NotBlank(message = "Email Cannot Be Empty")
    private String email;
    @NotNull(message = "Passowrd Should Not Be Null")
    @NotBlank(message = "Password Cannot Be Empty")
    String password;
}
