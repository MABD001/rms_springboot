package com.dasa.rms.Customer;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ")
    @SequenceGenerator(sequenceName = "customer_sequence", allocationSize = 1, name = "CUSTOMER_SEQ")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String lastTable;

}
