package com.example.berkay.Flight.repository;

import com.example.berkay.Flight.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDepartureCity(String departureCity);
}

