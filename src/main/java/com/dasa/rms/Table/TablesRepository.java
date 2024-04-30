package com.dasa.rms.Table;

import com.dasa.rms.Menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TablesRepository extends JpaRepository<Tables,Long> {
    @Query("SELECT t FROM Tables t")
    List<Tables> getAllTables();
}
