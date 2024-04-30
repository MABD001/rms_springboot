package com.dasa.rms.Table;

import com.dasa.rms.Account.Users.UsersRepository;
import com.dasa.rms.configurations.ResponseObject;
import com.dasa.rms.configurations.logs.LogLevel4j;
import com.dasa.rms.configurations.logs.SL4J;
import com.dasa.rms.configurations.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TablesService {
    @Autowired
    private final SL4J sl4J;
    private final UsersRepository usersRepository;
    private final JwtUtil jwtService;
    private final TablesRepository tablesRepository;
    public ResponseObject addNew(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, String description) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, TablesService.class, "addNew", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, TablesService.class, "addNew", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Tables tables = new Tables();
            tables.setDescription(description);
            tables.setCreatedBy(null);
//        Tables.setCreatedBy(user.get());
            Tables savedTables = tablesRepository.save(tables);
            if(savedTables.getId()!=null && savedTables!=null){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Tables created successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, TablesService.class, "addNew", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Create Tables");
                sl4J.writeLog(LogLevel4j.ERROR, TablesService.class, "addNew", String.valueOf("Unable To Create Tables"));
                return responseObject;
            }
        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Create Tables");
            sl4J.writeLog(LogLevel4j.ERROR, TablesService.class, "addNew", String.valueOf("Unable To Create Tables"));
            return responseObject;

        }
    }
    public ResponseObject getAll(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, TablesService.class, "getAll", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, TablesService.class, "getAll", String.valueOf("Access Denied"));
//            return responseObject;
//        }
//        Tables.setCreatedBy(user.get());
            List<Tables> savedTables = tablesRepository.getAllTables();
            if(!savedTables.isEmpty()){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Fetched Tables List Successfully");
                responseObject.setData(savedTables);
                sl4J.writeLog(LogLevel4j.INFO, TablesService.class, "getAll", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Fetched Tables List");
                sl4J.writeLog(LogLevel4j.ERROR, TablesService.class, "getAll", String.valueOf("Unable To Fetched Tables List"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Fetched Tables List");
            sl4J.writeLog(LogLevel4j.ERROR, TablesService.class, "getAll", String.valueOf("Unable To Fetched Tables List"));
            return responseObject;

        }
    }
    public ResponseObject update(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, Tables Tables) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, TablesService.class, "update", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, TablesService.class, "update", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Tables savedTables = tablesRepository.save(Tables);
            if(savedTables.getId()!=null && savedTables!=null){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Tables updated successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, TablesService.class, "update", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Update Tables");
                sl4J.writeLog(LogLevel4j.ERROR, TablesService.class, "update", String.valueOf("Unable To Update Tables"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Update Tables");
            sl4J.writeLog(LogLevel4j.ERROR, TablesService.class, "update", String.valueOf("Unable To Update Tables"));
            return responseObject;

        }
    }
    public ResponseObject delete(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, long Id) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, TablesService.class, "delete", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, TablesService.class, "delete", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Optional<Tables> optionalTables = tablesRepository.findById(Id);
            if(optionalTables.isEmpty()){
                responseObject.setCode("404");
                responseObject.setStatus(false);
                responseObject.setMessage("Tables not found");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.ERROR, TablesService.class, "delete", "Tables not found");
                return responseObject;

            }
            tablesRepository.delete(optionalTables.get());
            Optional<Tables> savedTables = tablesRepository.findById(Id);
            if(savedTables.isEmpty()){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Tables deleted successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, TablesService.class, "delete", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Delete Tables");
                sl4J.writeLog(LogLevel4j.ERROR, TablesService.class, "delete", String.valueOf("Unable To Delete Tables"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Delete Tables");
            sl4J.writeLog(LogLevel4j.ERROR, TablesService.class, "delete", String.valueOf("Unable To Delete Tables"));
            return responseObject;

        }

    }
}
