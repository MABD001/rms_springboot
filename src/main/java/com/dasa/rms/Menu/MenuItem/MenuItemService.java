package com.dasa.rms.Menu.MenuItem;

import com.dasa.rms.Account.Users.UsersRepository;
import com.dasa.rms.Menu.Menu;
import com.dasa.rms.Menu.MenuRepository;
import com.dasa.rms.configurations.ResponseObject;
import com.dasa.rms.configurations.logs.LogLevel4j;
import com.dasa.rms.configurations.logs.SL4J;
import com.dasa.rms.configurations.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuItemService {
    @Autowired
    private final SL4J sl4J;
    private final UsersRepository usersRepository;
    private final JwtUtil jwtService;
    private final MenuItemRepository menuItemRepository;
    private final MenuRepository menuRepository;
    public ResponseObject addNew(HttpServletRequest httpServletRequest,
                                     ResponseObject responseObject,CreateNewMenuItem createNewMenuItem) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, MenuItemService.class, "addNewMenu", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, MenuItemService.class, "addNewMenu", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Optional<Menu> menuOptional = menuRepository.findById(createNewMenuItem.getMenuId());
            if(menuOptional.isEmpty()){
                responseObject.setCode("404");
                responseObject.setStatus(false);
                responseObject.setMessage("Menu Item not found");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.ERROR, MenuItemService.class, "addNew", "Menu Item not found");
                return responseObject;

            }
            Menu menu = menuOptional.get();
            MenuItem menuItem = new MenuItem();
            menuItem.setDescription(createNewMenuItem.getDescription());
            menuItem.setMenuId(createNewMenuItem.getMenuId());
            menuItem.setPrice(createNewMenuItem.getPrice());
            menuItem.setCreatedBy(0);
//        menu.setCreatedBy(user.get());
            MenuItem savedMenuItem = menuItemRepository.save(menuItem);
            if(savedMenuItem.getId()!=null && savedMenuItem!=null){
                List<MenuItem> menuItemList = menu.getMenuItem();
                menuItemList.add(savedMenuItem);
                menu.setMenuItem(menuItemList);
                Menu savedMenu = menuRepository.save(menu);
                if(savedMenu.getId()==null || savedMenu==null){
                    responseObject.setCode("500");
                    responseObject.setStatus(false);
                    responseObject.setData(null);
                    responseObject.setMessage("Unable To Create Menu Item");
                    sl4J.writeLog(LogLevel4j.ERROR, MenuItemService.class, "addNew", String.valueOf("Unable To Create Menu Item"));
                    return responseObject;
                }
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Menu Item created successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, MenuItemService.class, "addNew", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Create Menu Item");
                sl4J.writeLog(LogLevel4j.ERROR, MenuItemService.class, "addNew", String.valueOf("Unable To Create Menu Item"));
                return responseObject;
            }
        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Create Menu Item");
            sl4J.writeLog(LogLevel4j.ERROR, MenuItemService.class, "addNew", String.valueOf("Unable To Create Menu Item"));
            return responseObject;

        }
    }
    public ResponseObject getAll(HttpServletRequest httpServletRequest,
                                     ResponseObject responseObject,long menuId) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, MenuItemService.class, "getAll", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, MenuItemService.class, "getAll", String.valueOf("Access Denied"));
//            return responseObject;
//        }
//        menu.setCreatedBy(user.get());
            List<MenuItem> savedMenuItem = menuItemRepository.getAllMenuItems(menuId);
            if(!savedMenuItem.isEmpty()){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Fetched Menu Item List Successfully");
                responseObject.setData(savedMenuItem);
                sl4J.writeLog(LogLevel4j.INFO, MenuItemService.class, "getAll", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Fetch Menu Item List");
                sl4J.writeLog(LogLevel4j.ERROR, MenuItemService.class, "getAll", String.valueOf("Unable To Fetch Menu Item List"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Fetch Menu List");
            sl4J.writeLog(LogLevel4j.ERROR, MenuItemService.class, "getAll", String.valueOf("Unable To Fetch Menu Item List"));
            return responseObject;

        }
    }
    public ResponseObject update(HttpServletRequest httpServletRequest,
                                     ResponseObject responseObject, MenuItem menuItem) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, MenuItemService.class, "update", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, MenuItemService.class, "update", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            MenuItem savedMenuItem = menuItemRepository.save(menuItem);
            if(savedMenuItem.getId()!=null && savedMenuItem!=null){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Menu Item updated successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, MenuItemService.class, "update", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Update Menu Item");
                sl4J.writeLog(LogLevel4j.ERROR, MenuItemService.class, "update", String.valueOf("Unable To Update Menu Item"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Update Menu Item");
            sl4J.writeLog(LogLevel4j.ERROR, MenuItemService.class, "update", String.valueOf("Unable To Update Menu Item"));
            return responseObject;

        }
    }
    public ResponseObject delete(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, long Id) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, MenuItemService.class, "delete", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, MenuItemService.class, "delete", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(Id);
            if(optionalMenuItem.isEmpty()){
                responseObject.setCode("404");
                responseObject.setStatus(false);
                responseObject.setMessage("Menu Item not found");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.ERROR, MenuItemService.class, "delete", "Menu Item not found");
                return responseObject;

            }
            menuItemRepository.delete(optionalMenuItem.get());
            Optional<MenuItem> savedMenuItem = menuItemRepository.findById(Id);
            if(savedMenuItem.isEmpty()){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Menu Item deleted successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, MenuItemService.class, "delete", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Delete Menu Item");
                sl4J.writeLog(LogLevel4j.ERROR, MenuItemService.class, "delete", String.valueOf("Unable To Delete Menu Item"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Delete Menu Item");
            sl4J.writeLog(LogLevel4j.ERROR, MenuItemService.class, "delete", String.valueOf("Unable To Delete Menu Item"));
            return responseObject;

        }

    }
    
}
