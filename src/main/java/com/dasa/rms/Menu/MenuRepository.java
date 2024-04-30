package com.dasa.rms.Menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Long> {
    @Query("SELECT m FROM Menu m")
    List<Menu> getAllMenus();

}
