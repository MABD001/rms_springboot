package com.dasa.rms.Reservation;

import com.dasa.rms.Account.Users.Users;
import com.dasa.rms.Customer.Customer;
import com.dasa.rms.Feedback.Feedback;
import com.dasa.rms.Table.Tables;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationResponse {
    private long Id;
    private Feedback feedback;
    private Customer customer;
    private Tables table;
    private Date reservationTime;
    private int numberOfGuests;
    private String status;
    private Users createdBy;
}
