package com.dasa.rms.Reservation;

import com.dasa.rms.Account.Users.UsersRepository;
import com.dasa.rms.Customer.Customer;
import com.dasa.rms.Customer.CustomerRepository;
import com.dasa.rms.Reservation.Reservation;
import com.dasa.rms.Table.Tables;
import com.dasa.rms.Table.TablesRepository;
import com.dasa.rms.configurations.ResponseObject;
import com.dasa.rms.configurations.logs.LogLevel4j;
import com.dasa.rms.configurations.logs.SL4J;
import com.dasa.rms.configurations.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    @Autowired
    private final SL4J sl4J;
    private final UsersRepository usersRepository;
    private final JwtUtil jwtService;
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final TablesRepository tablesRepository;

    public ResponseObject addNew(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, CreateNewReservationRequest createNewReservationRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, ReservationService.class, "addNew", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, ReservationService.class, "addNew", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Reservation reservation = new Reservation();
            reservation.setReservationTime(createNewReservationRequest.getReservationTime());
            reservation.setStatus("Created");
            reservation.setCustomerId(createNewReservationRequest.getCustomerId());
            reservation.setFeedback(null);
            reservation.setNumberOfGuests(createNewReservationRequest.getNumberOfGuests());
            reservation.setTableId(createNewReservationRequest.getTableId());
            reservation.setCreatedBy(null);
//        Reservation.setCreatedBy(user.get());
            Reservation savedReservation = reservationRepository.save(reservation);
            if(savedReservation.getId()!=null && savedReservation!=null){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Reservation created successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, ReservationService.class, "addNew", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Create Reservation");
                sl4J.writeLog(LogLevel4j.ERROR, ReservationService.class, "addNew", String.valueOf("Unable To Create Reservation"));
                return responseObject;
            }
        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Create Reservation");
            sl4J.writeLog(LogLevel4j.ERROR, ReservationService.class, "addNew", String.valueOf("Unable To Create Reservation"));
            return responseObject;

        }
    }
    public ResponseObject getAll(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, ReservationService.class, "getAll", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, ReservationService.class, "getAll", String.valueOf("Access Denied"));
//            return responseObject;
//        }
//        Reservation.setCreatedBy(user.get());
            List<Reservation> savedReservation = reservationRepository.getAllReservations();
            List<ReservationResponse> reservationResponseList = new ArrayList<>();
            if(!savedReservation.isEmpty()){
                for(Reservation reservation:savedReservation){
                    ReservationResponse reservationResponse = new ReservationResponse();
                    BeanUtils.copyProperties(reservation, reservationResponse);
                    Optional<Customer> optionalCustomer = customerRepository.findById(reservation.getCustomerId());
                    Optional<Tables> optionalTables = tablesRepository.findById(reservation.getTableId());
                    if(optionalCustomer.isEmpty() || optionalTables.isEmpty()){
                        continue;
                    }
                    reservationResponse.setCustomer(optionalCustomer.get());
                    reservationResponse.setTable(optionalTables.get());
                    reservationResponseList.add(reservationResponse);
                }

                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Fetched Reservation List Successfully");
                responseObject.setData(reservationResponseList);
                sl4J.writeLog(LogLevel4j.INFO, ReservationService.class, "getAll", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Fetched Reservation List");
                sl4J.writeLog(LogLevel4j.ERROR, ReservationService.class, "getAll", String.valueOf("Unable To Fetched Reservation List"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Fetched Reservation List");
            sl4J.writeLog(LogLevel4j.ERROR, ReservationService.class, "getAll", String.valueOf("Unable To Fetched Reservation List"));
            return responseObject;

        }
    }
    public ResponseObject update(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, Reservation Reservation) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, ReservationService.class, "update", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, ReservationService.class, "update", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Reservation savedReservation = reservationRepository.save(Reservation);
            if(savedReservation.getId()!=null && savedReservation!=null){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Reservation updated successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, ReservationService.class, "update", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Update Reservation");
                sl4J.writeLog(LogLevel4j.ERROR, ReservationService.class, "update", String.valueOf("Unable To Update Reservation"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Update Reservation");
            sl4J.writeLog(LogLevel4j.ERROR, ReservationService.class, "update", String.valueOf("Unable To Update Reservation"));
            return responseObject;

        }
    }
    public ResponseObject delete(HttpServletRequest httpServletRequest,
                                 ResponseObject responseObject, long Id) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, ReservationService.class, "delete", "API-> " + endpoint);
//        final String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = authHeader.substring(7);
//        long userId = Long.parseLong(jwtService.extactId(jwt));
//        Optional<Users> user = usersRepository.findById(userId);
//        if(user.isEmpty()){
//            responseObject.setCode("403");
//            responseObject.setStatus(false);
//            responseObject.setData(null);
//            responseObject.setMessage("Access Denied");
//            sl4J.writeLog(LogLevel4j.ERROR, ReservationService.class, "delete", String.valueOf("Access Denied"));
//            return responseObject;
//        }
            Optional<Reservation> optionalReservation = reservationRepository.findById(Id);
            if(optionalReservation.isEmpty()){
                responseObject.setCode("404");
                responseObject.setStatus(false);
                responseObject.setMessage("Reservation not found");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.ERROR, ReservationService.class, "delete", "Reservation not found");
                return responseObject;

            }
            reservationRepository.delete(optionalReservation.get());
            Optional<Reservation> savedReservation = reservationRepository.findById(Id);
            if(savedReservation.isEmpty()){
                responseObject.setCode("200");
                responseObject.setStatus(true);
                responseObject.setMessage("Reservation deleted successfully");
                responseObject.setData(null);
                sl4J.writeLog(LogLevel4j.INFO, ReservationService.class, "delete", responseObject.responseObjectMethod());
                return responseObject;
            }
            else {
                responseObject.setCode("500");
                responseObject.setStatus(false);
                responseObject.setData(null);
                responseObject.setMessage("Unable To Delete Reservation");
                sl4J.writeLog(LogLevel4j.ERROR, ReservationService.class, "delete", String.valueOf("Unable To Delete Reservation"));
                return responseObject;
            }

        }catch (Exception e){
            responseObject.setCode("500");
            responseObject.setStatus(false);
            responseObject.setData(null);
            responseObject.setMessage("Unable To Delete Reservation");
            sl4J.writeLog(LogLevel4j.ERROR, ReservationService.class, "delete", String.valueOf("Unable To Delete Reservation"));
            return responseObject;

        }

    }
}
