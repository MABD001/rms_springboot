package com.dasa.rms.Account.Login;

import com.dasa.rms.Account.Users.Users;
import com.dasa.rms.Account.Users.UsersRepository;
import com.dasa.rms.configurations.ResponseObject;
import com.dasa.rms.configurations.logs.LogLevel4j;
import com.dasa.rms.configurations.logs.SL4J;
import com.dasa.rms.configurations.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor

public class loginService {
    @Autowired
    private final SL4J sl4J;
    private final UsersRepository usersRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public ResponseObject login(AccountLoginRequest loginRequest, HttpServletRequest httpServletRequest, ResponseObject responseObject) {
        try {
            HttpSession session = httpServletRequest.getSession();
            String sessionId = session.getId();

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(), loginRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                Optional<Users> userPersonalInfo = usersRepository.findByEmail(loginRequest.getEmail());
                if (userPersonalInfo.isPresent()) {
                    AccountLoginResponse loginResponse = new AccountLoginResponse();
                    loginResponse.setJwt(jwtUtil.generateToken(userPersonalInfo.get(),userPersonalInfo.get().getId()));
                    loginResponse.setUserId(userPersonalInfo.get());
                    loginResponse.setName(userPersonalInfo.get().getFirstName());
                    loginResponse.setEmail(userPersonalInfo.get().getEmail());

                    responseObject.setCode("200");
                    responseObject.setStatus(true);
                    responseObject.setMessage(null);
                    responseObject.setData(loginResponse);
                    sl4J.writeLog(LogLevel4j.INFO, loginService.class, "login", responseObject.responseObjectMethod());
                    return responseObject;
                } else {
                    responseObject.setCode("200");
                    responseObject.setStatus(false);
                    responseObject.setData(null);
                    responseObject.setMessage("Account Deactivated!");
                    sl4J.writeLog(LogLevel4j.INFO, loginService.class, "login", responseObject.responseObjectMethod());
                    return responseObject;
                }
            } else {
                responseObject.setCode("401");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("User Email Or Password Incorrect");
                sl4J.writeLog(LogLevel4j.INFO, loginService.class, "login", responseObject.responseObjectMethod());
                return responseObject;
            }
        } catch (BadCredentialsException badCredentialsException) {
            responseObject.setCode("401");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Invalid email or password");
            sl4J.writeLog(LogLevel4j.ERROR, loginService.class, "login", badCredentialsException.getMessage());
            return responseObject;
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, loginService.class, "login", String.valueOf(exception));
            return (responseObject);
        }
    }

}
