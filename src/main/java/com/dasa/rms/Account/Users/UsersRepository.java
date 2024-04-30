package com.dasa.rms.Account.Users;

import com.dasa.rms.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);

    @Query("SELECT u FROM Users u")
    List<Users> getAllUsers();
}
