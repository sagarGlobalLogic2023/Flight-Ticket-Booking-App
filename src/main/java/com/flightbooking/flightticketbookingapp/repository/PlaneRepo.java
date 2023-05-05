package com.flightbooking.flightticketbookingapp.repository;

import com.flightbooking.flightticketbookingapp.entity.Plane;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Transactional
@Repository
public interface PlaneRepo extends JpaRepository<Plane,Long> {
    @Modifying
    @Query(value = "update plane set status =:n where plane_id = :u ",nativeQuery = true)
    public void changePlaneStatus(@Param("n") String status, @Param("u") Long id);
}
