package com.dasa.rms.Account.Users;

import com.dasa.rms.configurations.ResponseObject;
import com.dasa.rms.configurations.logs.LogLevel4j;
import com.dasa.rms.configurations.logs.SL4J;
import com.dasa.rms.configurations.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    @Autowired
    private final SL4J sl4J;
    private final UsersRepository usersRepository;
    private final JwtUtil jwtService;
    private final PasswordEncoder passwordEncoder;
    public ResponseObject addNew(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, CreateUserRequest createUserRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, UsersService.class, "addNew", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, UsersService.class, "addNew", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Users users = new Users();
            users.setEmail(createUserRequest.getEmail());
            users.setFirstName(createUserRequest.getFirstName());
            users.setLastName(createUserRequest.getLastName());
            users.setUserType(createUserRequest.getUserType());
            users.setEnabled(true);
            users.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
            Users savedUsers = usersRepository.save(users);
            if(savedUsers.getId()!=null && savedUsers!=null){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Users created successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, UsersService.class, "addNew", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Create Users");
                sl4J.writeLog(LogLevel4j.ERROR, UsersService.class, "addNew", String.valueOf("Unable To Create Users"));
                return responseObject;
            }
        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Create Users");
            sl4J.writeLog(LogLevel4j.ERROR, UsersService.class, "addNew", String.valueOf("Unable To Create Users"));
            return responseObject;

        }
    }
    public ResponseObject getAll(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, UsersService.class, "getAll", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, UsersService.class, "getAll", String.valueOf("Access Denied"));
//            return responseObject;
//        }
//        users.setCreatedBy(user.get());
            List<Users> savedUsers = usersRepository.getAllUsers();
            if(!savedUsers.isEmpty()){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Fetched Users List Successfully");
                responseObject.setData(savedUsers);
                sl4J.writeLog(LogLevel4j.INFO, UsersService.class, "getAll", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Fetched Users List");
                sl4J.writeLog(LogLevel4j.ERROR, UsersService.class, "getAll", String.valueOf("Unable To Fetched Users List"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Fetched Users List");
            sl4J.writeLog(LogLevel4j.ERROR, UsersService.class, "getAll", String.valueOf("Unable To Fetched Users List"));
            return responseObject;

        }
    }
    public ResponseObject update(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, Users users) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, UsersService.class, "update", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, UsersService.class, "update", String.valueOf("Access Denied"));
//            return responseObject;
//        }

            Users savedUsers = usersRepository.save(users);
            if(savedUsers.getId()!=null && savedUsers!=null){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Users updated successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, UsersService.class, "update", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Update Users");
                sl4J.writeLog(LogLevel4j.ERROR, UsersService.class, "update", String.valueOf("Unable To Update Users"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Update Users");
            sl4J.writeLog(LogLevel4j.ERROR, UsersService.class, "update", String.valueOf("Unable To Update Users"));
            return responseObject;

        }
    }
    public ResponseObject delete(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, long Id) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, UsersService.class, "delete", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, UsersService.class, "delete", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Optional<Users> optionalUsers = usersRepository.findById(Id);
            if(optionalUsers.isEmpty()){
                responseObject.setCode("404");
                responseObject.setStatus(false);
                responseObject.setMessage("Users not found");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.ERROR, UsersService.class, "delete", "Users not found");
                return responseObject;

            }
            usersRepository.delete(optionalUsers.get());
            Optional<Users> savedUsers = usersRepository.findById(Id);
            if(savedUsers.isEmpty()){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Users deleted successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, UsersService.class, "delete", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Delete Users");
                sl4J.writeLog(LogLevel4j.ERROR, UsersService.class, "delete", String.valueOf("Unable To Delete Users"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Delete Users");
            sl4J.writeLog(LogLevel4j.ERROR, UsersService.class, "delete", String.valueOf("Unable To Delete Users"));
            return responseObject;

        }

    }

}
