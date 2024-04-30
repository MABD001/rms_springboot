package com.dasa.rms.Menu;

import com.dasa.rms.Account.Users.UsersRepository;
import com.dasa.rms.configurations.ResponseObject;
import com.dasa.rms.configurations.logs.LogLevel4j;
import com.dasa.rms.configurations.logs.SL4J;
import com.dasa.rms.configurations.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {
    @Autowired
    private final SL4J sl4J;
    private final UsersRepository usersRepository;
    private final JwtUtil jwtService;
    private final MenuRepository menuRepository;

    public ResponseObject addNewMenu(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, String description) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, MenuService.class, "addNewMenu", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, MenuService.class, "addNewMenu", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Menu menu = new Menu();
            menu.setDescription(description);
            menu.setMenuItem(new ArrayList<>());
            menu.setCreatedBy(null);
//        menu.setCreatedBy(user.get());
            Menu savedMenu = menuRepository.save(menu);
            if(savedMenu.getId()!=null && savedMenu!=null){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Menu created successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, MenuService.class, "addNewMenu", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Create Menu");
                sl4J.writeLog(LogLevel4j.ERROR, MenuService.class, "addNewMenu", String.valueOf("Unable To Create Menu"));
                return responseObject;
            }
        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Create Menu");
            sl4J.writeLog(LogLevel4j.ERROR, MenuService.class, "addNewMenu", String.valueOf("Unable To Create Menu"));
            return responseObject;

        }
    }
    public ResponseObject getAllMenu(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, MenuService.class, "getAllMenu", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, MenuService.class, "getAllMenu", String.valueOf("Access Denied"));
//            return responseObject;
//        }
//        menu.setCreatedBy(user.get());
            List<Menu> savedMenu = menuRepository.getAllMenus();
            if(!savedMenu.isEmpty()){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Fetched Menu List Successfully");
                responseObject.setData(savedMenu);
                sl4J.writeLog(LogLevel4j.INFO, MenuService.class, "getAllMenu", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Fetched Menu List");
                sl4J.writeLog(LogLevel4j.ERROR, MenuService.class, "getAllMenu", String.valueOf("Unable To Fetched Menu List"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Fetched Menu List");
            sl4J.writeLog(LogLevel4j.ERROR, MenuService.class, "getAllMenu", String.valueOf("Unable To Fetched Menu List"));
            return responseObject;

        }
    }
    public ResponseObject updateMenu(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, Menu menu) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, MenuService.class, "updateMenu", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, MenuService.class, "updateMenu", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Menu savedMenu = menuRepository.save(menu);
            if(savedMenu.getId()!=null && savedMenu!=null){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Menu updated successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, MenuService.class, "updateMenu", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Update Menu");
                sl4J.writeLog(LogLevel4j.ERROR, MenuService.class, "updateMenu", String.valueOf("Unable To Update Menu"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Update Menu");
            sl4J.writeLog(LogLevel4j.ERROR, MenuService.class, "updateMenu", String.valueOf("Unable To Update Menu"));
            return responseObject;

        }
    }
    public ResponseObject delete(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, long Id) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, MenuService.class, "delete", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, MenuService.class, "delete", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Optional<Menu> optionalMenu = menuRepository.findById(Id);
            if(optionalMenu.isEmpty()){
                responseObject.setCode("404");
                responseObject.setStatus(false);
                responseObject.setMessage("Menu not found");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.ERROR, MenuService.class, "delete", "Menu not found");
                return responseObject;

            }
            menuRepository.delete(optionalMenu.get());
            Optional<Menu> savedMenu = menuRepository.findById(Id);
            if(savedMenu.isEmpty()){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Menu deleted successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, MenuService.class, "delete", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Delete Menu");
                sl4J.writeLog(LogLevel4j.ERROR, MenuService.class, "delete", String.valueOf("Unable To Delete Menu"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Delete Menu");
            sl4J.writeLog(LogLevel4j.ERROR, MenuService.class, "delete", String.valueOf("Unable To Delete Menu"));
            return responseObject;

        }

    }
}
