package com.dasa.rms.Reservation;

import lombok.Data;

import java.util.Date;

@Data
public class CreateNewReservationRequest {
    private long customerId;
    private long tableId;
    private Date reservationTime;
    private int numberOfGuests;
}
