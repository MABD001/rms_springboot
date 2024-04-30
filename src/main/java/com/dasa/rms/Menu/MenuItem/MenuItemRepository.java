package com.dasa.rms.Menu.MenuItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {
    @Query("SELECT m FROM MenuItem m where m.menuId=:menuId")
    List<MenuItem> getAllMenuItems(@Param("menuId") long menuId);
}
