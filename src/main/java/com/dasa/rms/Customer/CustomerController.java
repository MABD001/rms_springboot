package com.dasa.rms.Customer;

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
@RequestMapping("/Customer")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Customer", description = "Customer APIs")
public class CustomerController {
    private final SL4J sl4J;
    private final ResponseEntityResult responseEntityResult;
    private final ResponseObject responseObject; // Generic Response Object
    private final CustomerService customerService;
    @PostMapping("/addNewCustomer")
    private ResponseEntity<?> addNewCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, CustomerController.class, "addNewCustomer", "API-> " + endpoint);
            return responseEntityResult.responseEntity(customerService.addNew(httpServletRequest, responseObject, createCustomerRequest));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, CustomerController.class, "addNewCustomer", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }

    @GetMapping("/getAllCustomer")
    public ResponseEntity<?> getAllCustomer(HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, CustomerController.class, "getAllCustomer", "API-> " + endpoint);
            return responseEntityResult.responseEntity(customerService.getAll(httpServletRequest, responseObject));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, CustomerController.class, "getAllCustomer", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);

        }
    }


    @PutMapping("/updateCustomer")
    private ResponseEntity<?> updateCustomer(@Valid @RequestBody Customer menu, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, CustomerController.class, "updateCustomer", "API-> " + endpoint);
            return responseEntityResult.responseEntity(customerService.update(httpServletRequest, responseObject, menu));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, CustomerController.class, "updateCustomer", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, CustomerController.class, "deleteById", "API-> " + endpoint);
            return responseEntityResult.responseEntity(customerService.delete(httpServletRequest, responseObject, id));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, CustomerController.class, "deleteById", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }

}
