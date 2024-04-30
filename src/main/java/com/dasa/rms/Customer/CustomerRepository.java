package com.dasa.rms.Customer;

import com.dasa.rms.Menu.MenuItem.MenuItem;
import com.dasa.rms.Reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT c FROM Customer c")
    List<Customer> getAllCustomers();
}
