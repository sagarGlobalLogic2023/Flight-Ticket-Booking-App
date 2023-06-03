package com.flightbooking.flightticketbookingapp.repository;

import com.flightbooking.flightticketbookingapp.entity.Booking;
import com.flightbooking.flightticketbookingapp.entity.BookingId;
import com.flightbooking.flightticketbookingapp.entity.Flight;
import com.flightbooking.flightticketbookingapp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, BookingId> {


//    @Query(value = "select * from booking where user_id=:n", nativeQuery = true)
//    List<Booking> getAllByUserid(@Param("n") Long userId);
    List<Booking> findAllByUser(User userId);


    Booking findByUserAndFlight(User userId, Flight flightId);

    List<Booking> findAllByUserAndFlight(User userId, Flight flightId);
}
