package com.dasa.rms.Table;

import com.dasa.rms.Account.Users.Users;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TABLES_SEQ")
    @SequenceGenerator(sequenceName = "tables_sequence", allocationSize = 1, name = "TABLES_SEQ")
    private Long id;
    private String description;
    @ManyToOne
    private Users createdBy;
}
