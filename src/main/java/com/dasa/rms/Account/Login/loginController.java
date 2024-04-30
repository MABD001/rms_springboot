package com.dasa.rms.Account.Login;

import com.dasa.rms.Account.Users.UsersService;
import com.dasa.rms.configurations.ResponseEntityResult;
import com.dasa.rms.configurations.ResponseObject;
import com.dasa.rms.configurations.logs.LogLevel4j;
import com.dasa.rms.configurations.logs.SL4J;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Login")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Login", description = "Login APIs")
public class loginController {
    private final ResponseObject responseObject; // Generic Response Object
    private final ResponseEntityResult responseEntityResult;
    private final SL4J sl4J;
    private final loginService loginService;


    @PostMapping("/login")
    public ResponseEntity<?> loginAccount(@Valid @RequestBody AccountLoginRequest loginRequest, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, loginController.class, "loginAccount", "API-> " + endpoint);
            return responseEntityResult.responseEntity(loginService.login(loginRequest, httpServletRequest, responseObject));
        } catch (Exception exception) {
            sl4J.writeLog(LogLevel4j.ERROR, loginController.class, "loginAccount", String.valueOf(exception));
            responseObject.setCode("500");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(responseObject);
        }
    }

}
