package com.dasa.rms.Menu.MenuItem;

import com.dasa.rms.Account.Users.Users;
import com.dasa.rms.Menu.Menu;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MENU_ITEM_SEQ")
    @SequenceGenerator(sequenceName = "menu_item_sequence", allocationSize = 1, name = "MENU_ITEM_SEQ")
    private Long id;
    private String description;
    private int price;
    private long menuId;
    private long createdBy;
}
