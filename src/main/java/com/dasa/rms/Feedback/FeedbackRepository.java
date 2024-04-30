package com.dasa.rms.Feedback;

import com.dasa.rms.Reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
}
