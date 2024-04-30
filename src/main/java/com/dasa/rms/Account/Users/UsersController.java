package com.dasa.rms.Account.Users;

import com.dasa.rms.configurations.ResponseEntityResult;
import com.dasa.rms.configurations.ResponseObject;
import com.dasa.rms.configurations.logs.LogLevel4j;
import com.dasa.rms.configurations.logs.SL4J;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Users")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "User", description = "Account | User APIs")
public class UsersController {
    private final SL4J sl4J;
    private final ResponseEntityResult responseEntityResult;
    private final ResponseObject responseObject; // Generic Response Object
    private final UsersService usersService;

    @PostMapping("/addNewUsers")
    private ResponseEntity<?> addNewUsers(@Valid @RequestBody CreateUserRequest createUserRequest, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, UsersController.class, "addNewUsers", "API-> " + endpoint);
            return responseEntityResult.responseEntity(usersService.addNew(httpServletRequest, responseObject, createUserRequest));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, UsersController.class, "addNewUsers", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, UsersController.class, "getAllUsers", "API-> " + endpoint);
            return responseEntityResult.responseEntity(usersService.getAll(httpServletRequest, responseObject));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, UsersController.class, "getAllUsers", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);

        }
    }


    @PutMapping("/updateUsers")
    private ResponseEntity<?> updateUsers(@Valid @RequestBody Users users, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, UsersController.class, "updateUsers", "API-> " + endpoint);
            return responseEntityResult.responseEntity(usersService.update(httpServletRequest, responseObject, users));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, UsersController.class, "updateUsers", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, UsersController.class, "deleteById", "API-> " + endpoint);
            return responseEntityResult.responseEntity(usersService.delete(httpServletRequest, responseObject, id));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, UsersController.class, "deleteById", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }

}
