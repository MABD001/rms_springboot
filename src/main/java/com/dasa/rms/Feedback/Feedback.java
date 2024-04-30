package com.dasa.rms.Feedback;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Data
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FEEDBACK_SEQ")
    @SequenceGenerator(sequenceName = "feedback_sequence", allocationSize = 1, name = "FEEDBACK_SEQ")
    private Long id;
    private String feedback;
    private long customerId;
    @CreationTimestamp
    private Date createdAt;

}
