package com.dasa.rms.Feedback;

import com.dasa.rms.Account.Users.UsersRepository;
import com.dasa.rms.Customer.Customer;
import com.dasa.rms.Customer.CustomerRepository;
import com.dasa.rms.Reservation.Reservation;
import com.dasa.rms.Reservation.ReservationRepository;
import com.dasa.rms.Table.Tables;
import com.dasa.rms.Table.TablesRepository;
import com.dasa.rms.configurations.ResponseObject;
import com.dasa.rms.configurations.logs.LogLevel4j;
import com.dasa.rms.configurations.logs.SL4J;
import com.dasa.rms.configurations.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    @Autowired
    private final SL4J sl4J;
    private final UsersRepository usersRepository;
    private final JwtUtil jwtService;
    private final FeedbackRepository feedbackRepository;
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final TablesRepository tablesRepository;
    public ResponseObject addNew(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, CreateFeedbackRequest createFeedbackRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, FeedbackService.class, "addNew", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, FeedbackService.class, "addNew", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Feedback feedback = new Feedback();
            feedback.setFeedback(createFeedbackRequest.getFeedback());
            feedback.setCustomerId(createFeedbackRequest.getCustomerId());
//        feedback.setCreatedBy(user.get());
            Feedback savedFeedback = feedbackRepository.save(feedback);
            if(savedFeedback.getId()!=null && savedFeedback!=null){
                Optional<Reservation> optionalReservation = reservationRepository.findById(createFeedbackRequest.getReservation());
                if(optionalReservation.isEmpty()){
                    responseObject.setCode("500");
                    responseObject.setStatus(false);
                    responseObject.setData(null);
                    responseObject.setMessage("Unable To Create Feedback");
                    sl4J.writeLog(LogLevel4j.ERROR, FeedbackService.class, "addNew", String.valueOf("Unable To Create Feedback"));
                    return responseObject;

                }
                Reservation reservation = optionalReservation.get();
                Optional<Customer> optionalCustomer = customerRepository.findById(savedFeedback.getCustomerId());
                if(optionalReservation.isEmpty()){
                    responseObject.setCode("500");
                    responseObject.setStatus(false);
                    responseObject.setData(null);
                    responseObject.setMessage("Unable To Create Feedback");
                    sl4J.writeLog(LogLevel4j.ERROR, FeedbackService.class, "addNew", String.valueOf("Unable To Create Feedback"));
                    return responseObject;

                }
                Customer customer = optionalCustomer.get();
                Optional<Tables> optionalTables = tablesRepository.findById(reservation.getTableId());
                optionalTables.ifPresent(tables -> customer.setLastTable(tables.getDescription()));

                reservation.setFeedback(savedFeedback);
                reservation.setStatus("Completed");
                Reservation savedReservation = reservationRepository.save(reservation);
                if(savedReservation.getId()==null || savedReservation==null){
                    responseObject.setCode("500");
                    responseObject.setStatus(false);
                    responseObject.setData(null);
                    responseObject.setMessage("Unable To Create Feedback");
                    sl4J.writeLog(LogLevel4j.ERROR, FeedbackService.class, "addNew", String.valueOf("Unable To Create Feedback"));
                    return responseObject;

                }
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Feedback created successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, FeedbackService.class, "addNew", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Create Feedback");
                sl4J.writeLog(LogLevel4j.ERROR, FeedbackService.class, "addNew", String.valueOf("Unable To Create Feedback"));
                return responseObject;
            }
        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Create Feedback");
            sl4J.writeLog(LogLevel4j.ERROR, FeedbackService.class, "addNew", String.valueOf("Unable To Create Feedback"));
            return responseObject;

        }
    }
    public ResponseObject update(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, Feedback feedback) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, FeedbackService.class, "update", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, FeedbackService.class, "update", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Feedback savedFeedback = feedbackRepository.save(feedback);
            if(savedFeedback.getId()!=null && savedFeedback!=null){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Feedback updated successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, FeedbackService.class, "update", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Update Feedback");
                sl4J.writeLog(LogLevel4j.ERROR, FeedbackService.class, "update", String.valueOf("Unable To Update Feedback"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Update Feedback");
            sl4J.writeLog(LogLevel4j.ERROR, FeedbackService.class, "update", String.valueOf("Unable To Update Feedback"));
            return responseObject;

        }
    }
    public ResponseObject delete(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, long Id) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, FeedbackService.class, "delete", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, FeedbackService.class, "delete", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Optional<Feedback> optionalFeedback = feedbackRepository.findById(Id);
            if(optionalFeedback.isEmpty()){
                responseObject.setCode("404");
                responseObject.setStatus(false);
                responseObject.setMessage("Feedback not found");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.ERROR, FeedbackService.class, "delete", "Feedback not found");
                return responseObject;

            }
            feedbackRepository.delete(optionalFeedback.get());
            Optional<Feedback> savedFeedback = feedbackRepository.findById(Id);
            if(savedFeedback.isEmpty()){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Feedback deleted successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, FeedbackService.class, "delete", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Delete Feedback");
                sl4J.writeLog(LogLevel4j.ERROR, FeedbackService.class, "delete", String.valueOf("Unable To Delete Feedback"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Delete Feedback");
            sl4J.writeLog(LogLevel4j.ERROR, FeedbackService.class, "delete", String.valueOf("Unable To Delete Feedback"));
            return responseObject;

        }

    }
}
