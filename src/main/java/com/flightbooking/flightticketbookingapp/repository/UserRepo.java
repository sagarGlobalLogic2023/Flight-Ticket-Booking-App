package com.flightbooking.flightticketbookingapp.repository;

import com.flightbooking.flightticketbookingapp.entity.Booking;
import com.flightbooking.flightticketbookingapp.entity.Flight;

import com.flightbooking.flightticketbookingapp.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    @Modifying
    @Query(value = "update user set is_blocked =:n where user_id = :u ",nativeQuery = true)
    public void changeUserStatus(@Param("n") String status, @Param("u") Long id);

    public List<User> findByRole(String role);
    public User findByEmail(String email);



}

