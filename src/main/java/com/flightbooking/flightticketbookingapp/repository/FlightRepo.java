package com.flightbooking.flightticketbookingapp.repository;

import com.flightbooking.flightticketbookingapp.entity.Flight;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface FlightRepo extends JpaRepository<Flight, Long> {
    @Modifying
    @Query(value = "update flight set status =:n where flight_id = :u ",nativeQuery = true)
    public void changeFlightStatus(@Param("n") String status, @Param("u") Long id);

    List<Flight> findAllBySourceAndDestination(String source, String destination);
}
