package com.dasa.rms.Reservation;

import com.dasa.rms.Menu.Menu;
import com.dasa.rms.Table.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query("SELECT r FROM Reservation r")
    List<Reservation> getAllReservations();
}
