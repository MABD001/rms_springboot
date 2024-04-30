package com.dasa.rms.Menu.MenuItem;

import com.dasa.rms.Menu.Menu;
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
@RequestMapping("/MenuItem")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Menu Item", description = "Menu Item APIs")
public class MenuItemController {
    private final SL4J sl4J;
    private final ResponseEntityResult responseEntityResult;
    private final ResponseObject responseObject; // Generic Response Object
    private final MenuItemService menuItemService;

    @PostMapping("/addNewMenuItem")
    private ResponseEntity<?> addNewMenuItem(@Valid @RequestBody CreateNewMenuItem createNewMenuItem, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, MenuItemController.class, "addNewMenuItem", "API-> " + endpoint);
            return responseEntityResult.responseEntity(menuItemService.addNew(httpServletRequest, responseObject, createNewMenuItem));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, MenuItemController.class, "addNewMenuItem", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }

    @GetMapping("/getAllMenuItem")
    public ResponseEntity<?> getAllMenuItem(@Valid @RequestParam("menuId") long menuId,HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, MenuItemController.class, "getAllMenuItem", "API-> " + endpoint);
            return responseEntityResult.responseEntity(menuItemService.getAll(httpServletRequest, responseObject,menuId));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, MenuItemController.class, "getAllMenuItem", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);

        }
    }


    @PutMapping("/updateMenuItem")
    private ResponseEntity<?> updateMenuItem(@Valid @RequestBody MenuItem menuItem, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, MenuItemController.class, "updateMenuItem", "API-> " + endpoint);
            return responseEntityResult.responseEntity(menuItemService.update(httpServletRequest, responseObject, menuItem));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, MenuItemController.class, "updateMenuItem", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, MenuItemController.class, "deleteById", "API-> " + endpoint);
            return responseEntityResult.responseEntity(menuItemService.delete(httpServletRequest, responseObject, id));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, MenuItemController.class, "deleteById", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }

}
