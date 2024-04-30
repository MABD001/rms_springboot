package com.dasa.rms.Reservation;

import com.dasa.rms.Account.Users.Users;
import com.dasa.rms.Feedback.Feedback;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESERVATION_SEQ")
    @SequenceGenerator(sequenceName = "reservation_sequence", allocationSize = 1, name = "RESERVATION_SEQ")
    private Long id;
    @OneToOne
    private Feedback feedback;
    private long customerId;
    private long tableId;
    private Date reservationTime;
    private int numberOfGuests;
    private String status;
    @ManyToOne
    private Users createdBy;
    @CreationTimestamp
    private Date createdAt;

}
