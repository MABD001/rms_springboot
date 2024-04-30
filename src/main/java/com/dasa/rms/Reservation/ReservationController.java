package com.dasa.rms.Reservation;

import com.dasa.rms.Reservation.Reservation;
import com.dasa.rms.Reservation.ReservationController;
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
@RequestMapping("/Reservation")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Reservation", description = "Reservation APIs")
public class ReservationController {
    private final SL4J sl4J;
    private final ResponseEntityResult responseEntityResult;
    private final ResponseObject responseObject; // Generic Response Object
    private final ReservationService reservationService;
    @PostMapping("/addNewReservation")
    private ResponseEntity<?> addNewReservation(@Valid @RequestBody CreateNewReservationRequest createNewReservationRequest, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, ReservationController.class, "addNewReservation", "API-> " + endpoint);
            return responseEntityResult.responseEntity(reservationService.addNew(httpServletRequest, responseObject, createNewReservationRequest));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, ReservationController.class, "addNewReservation", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }

    @GetMapping("/getAllReservation")
    public ResponseEntity<?> getAllReservation(HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, ReservationController.class, "getAllReservation", "API-> " + endpoint);
            return responseEntityResult.responseEntity(reservationService.getAll(httpServletRequest, responseObject));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, ReservationController.class, "getAllReservation", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);

        }
    }


    @PutMapping("/updateReservation")
    private ResponseEntity<?> updateReservation(@Valid @RequestBody Reservation reservation, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, ReservationController.class, "updateReservation", "API-> " + endpoint);
            return responseEntityResult.responseEntity(reservationService.update(httpServletRequest, responseObject, reservation));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, ReservationController.class, "updateReservation", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id, HttpServletRequest httpServletRequest) {
        try {
            String endpoint = httpServletRequest.getRequestURI();
            sl4J.writeLog(LogLevel4j.INFO, ReservationController.class, "deleteById", "API-> " + endpoint);
            return responseEntityResult.responseEntity(reservationService.delete(httpServletRequest, responseObject, id));
        } catch (Exception exception) {
            responseObject.setCode("500");
            sl4J.writeLog(LogLevel4j.ERROR, ReservationController.class, "deleteById", String.valueOf(exception));
            return responseEntityResult.responseEntity(responseObject);
        }
    }

}
