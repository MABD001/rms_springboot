package com.dasa.rms.Menu;

import com.dasa.rms.Account.Users.Users;
import com.dasa.rms.Menu.MenuItem.MenuItem;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MENU_SEQ")
    @SequenceGenerator(sequenceName = "menu_sequence", allocationSize = 1, name = "MENU_SEQ")
    private Long id;
    private String description;
    @OneToMany
    private List<MenuItem> menuItem;
    @ManyToOne
    private Users createdBy;
}
