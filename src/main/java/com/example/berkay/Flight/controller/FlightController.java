package com.example.berkay.Flight.controller;

import com.example.berkay.Flight.entity.Flight;
import com.example.berkay.Flight.repository.FlightRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightController{
    private final FlightRepository repository;

    FlightController(FlightRepository repository){
        this.repository = repository;
    }

    //Endpoint to list all the flights
    @GetMapping("/flights")
    List<Flight> all(){
        return repository.findAll();
    }





//Endpoint to list a single flight based on its id

    @GetMapping("/flights/{id}")
    Flight one(@PathVariable Long id){

        return repository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));

    }

    @PutMapping("/flights/{id}")
    Flight replaceFlight(@RequestBody Flight newFlight, @PathVariable Long id) {

        return repository.findById(id)
                .map(flight -> {
                    flight.setDate(newFlight.getDate());
                    flight.setDepartureCity((newFlight.getDepatureCity()));
                    flight.setLandingCity(newFlight.getLandingCity());
                    flight.setDepartingTime((newFlight.getDepartingTime()));
                    flight.setFlightName((newFlight.getFlightName()));





                    return repository.save(flight);
                })
                .orElseGet(() -> {
                    newFlight.setId(id);
                    return repository.save(newFlight);
                });
    }

    //Endpoint to create a flight
    @PostMapping("/flights")
    Flight newFlight(@RequestBody Flight newFlight){
        return repository.save(newFlight);
    }


    //Endpoint to delete a flight
    @DeleteMapping("flights/{id}")
    void deleteFlight(@PathVariable Long id){
        repository.deleteById(id);
    }


}


