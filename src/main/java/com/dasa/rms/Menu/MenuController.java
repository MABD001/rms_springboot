package com.dasa.rms.Menu;

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

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Menu")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Menu", description = "Menu APIs")
public class MenuController {
    
    private final MenuService menuService;
    private final SL4J sl4J;
    private final ResponseEntityResult responseEntityResult;
    private final ResponseObject responseObject; // Generic Response Object

    @PostMapping("/addNewMenu")
    private ResponseEntity<?> addNewMenu(@Valid @RequestParam("description") String description, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, MenuController.class, "addNewMenu", "API-> " + endpoint);
            return responseEntityResult.responseEntity(menuService.addNewMenu(httpServletRequest, responseObject, description));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, MenuController.class, "addNewMenu", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }

    @GetMapping("/getAllMenu")
    public ResponseEntity<?> getAllMenu(HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, MenuController.class, "getAllMenu", "API-> " + endpoint);
            return responseEntityResult.responseEntity(menuService.getAllMenu(httpServletRequest, responseObject));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, MenuController.class, "getAllMenu", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);

        }
    }


    @PutMapping("/updateMenu")
    private ResponseEntity<?> updateMenu(@Valid @RequestBody Menu menu, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, MenuController.class, "updateMenu", "API-> " + endpoint);
            return responseEntityResult.responseEntity(menuService.updateMenu(httpServletRequest, responseObject, menu));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, MenuController.class, "updateMenu", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, MenuController.class, "deleteById", "API-> " + endpoint);
            return responseEntityResult.responseEntity(menuService.delete(httpServletRequest, responseObject, id));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, MenuController.class, "deleteById", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }


}
