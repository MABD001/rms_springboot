package com.dasa.rms.Table;

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
@RequestMapping("/Tables")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Tables", description = "Tables APIs")
public class TablesController {
    private final SL4J sl4J;
    private final ResponseEntityResult responseEntityResult;
    private final ResponseObject responseObject; // Generic Response Object
    private final TablesService tablesService;
    @PostMapping("/addNewTables")
    private ResponseEntity<?> addNewTables(@Valid @RequestParam("description") String description, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, TablesController.class, "addNewTables", "API-> " + endpoint);
            return responseEntityResult.responseEntity(tablesService.addNew(httpServletRequest, responseObject, description));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, TablesController.class, "addNewTables", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }

    @GetMapping("/getAllTables")
    public ResponseEntity<?> getAllTables(HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, TablesController.class, "getAllTables", "API-> " + endpoint);
            return responseEntityResult.responseEntity(tablesService.getAll(httpServletRequest, responseObject));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, TablesController.class, "getAllTables", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);

        }
    }


    @PutMapping("/updateTables")
    private ResponseEntity<?> updateTables(@Valid @RequestBody Tables tables, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, TablesController.class, "updateTables", "API-> " + endpoint);
            return responseEntityResult.responseEntity(tablesService.update(httpServletRequest, responseObject, tables));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, TablesController.class, "updateTables", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, TablesController.class, "deleteById", "API-> " + endpoint);
            return responseEntityResult.responseEntity(tablesService.delete(httpServletRequest, responseObject, id));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, TablesController.class, "deleteById", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }

}
