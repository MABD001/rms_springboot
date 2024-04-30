package com.dasa.rms.Customer;

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
public class CustomerService {
    @Autowired
    private final SL4J sl4J;
    private final UsersRepository usersRepository;
    private final JwtUtil jwtService;
    private final CustomerRepository customerRepository;

    public ResponseObject addNew(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, CreateCustomerRequest createCustomerRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, CustomerService.class, "addNew", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, CustomerService.class, "addNew", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Customer customer = new Customer();
            customer.setEmail(createCustomerRequest.getEmail());
            customer.setFirstName(createCustomerRequest.getFirstName());
            customer.setLastName(createCustomerRequest.getLastName());
            customer.setContactNumber(createCustomerRequest.getContactNumber());
            customer.setLastTable("");
//        customer.setCreatedBy(user.get());
            Customer savedCustomer = customerRepository.save(customer);
            if(savedCustomer.getId()!=null && savedCustomer!=null){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Customer created successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, CustomerService.class, "addNew", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Create Customer");
                sl4J.writeLog(LogLevel4j.ERROR, CustomerService.class, "addNew", String.valueOf("Unable To Create Customer"));
                return responseObject;
            }
        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Create Customer");
            sl4J.writeLog(LogLevel4j.ERROR, CustomerService.class, "addNew", String.valueOf("Unable To Create Customer"));
            return responseObject;

        }
    }
    public ResponseObject getAll(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, CustomerService.class, "getAll", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, CustomerService.class, "getAll", String.valueOf("Access Denied"));
//            return responseObject;
//        }
//        customer.setCreatedBy(user.get());
            List<Customer> savedCustomer = customerRepository.getAllCustomers();
            if(!savedCustomer.isEmpty()){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Fetched Customer List Successfully");
                responseObject.setData(savedCustomer);
                sl4J.writeLog(LogLevel4j.INFO, CustomerService.class, "getAll", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Fetched Customer List");
                sl4J.writeLog(LogLevel4j.ERROR, CustomerService.class, "getAll", String.valueOf("Unable To Fetched Customer List"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Fetched Customer List");
            sl4J.writeLog(LogLevel4j.ERROR, CustomerService.class, "getAll", String.valueOf("Unable To Fetched Customer List"));
            return responseObject;

        }
    }
    public ResponseObject update(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, Customer customer) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, CustomerService.class, "update", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, CustomerService.class, "update", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Customer savedCustomer = customerRepository.save(customer);
            if(savedCustomer.getId()!=null && savedCustomer!=null){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Customer updated successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, CustomerService.class, "update", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Update Customer");
                sl4J.writeLog(LogLevel4j.ERROR, CustomerService.class, "update", String.valueOf("Unable To Update Customer"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Update Customer");
            sl4J.writeLog(LogLevel4j.ERROR, CustomerService.class, "update", String.valueOf("Unable To Update Customer"));
            return responseObject;

        }
    }
    public ResponseObject delete(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, long Id) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, CustomerService.class, "delete", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, CustomerService.class, "delete", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Optional<Customer> optionalCustomer = customerRepository.findById(Id);
            if(optionalCustomer.isEmpty()){
                responseObject.setCode("404");
                responseObject.setStatus(false);
                responseObject.setMessage("Customer not found");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.ERROR, CustomerService.class, "delete", "Customer not found");
                return responseObject;

            }
            customerRepository.delete(optionalCustomer.get());
            Optional<Customer> savedCustomer = customerRepository.findById(Id);
            if(savedCustomer.isEmpty()){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Customer deleted successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, CustomerService.class, "delete", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Delete Customer");
                sl4J.writeLog(LogLevel4j.ERROR, CustomerService.class, "delete", String.valueOf("Unable To Delete Customer"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Delete Customer");
            sl4J.writeLog(LogLevel4j.ERROR, CustomerService.class, "delete", String.valueOf("Unable To Delete Customer"));
            return responseObject;

        }

    }
}
