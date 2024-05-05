package com.example.berkay.Flight.controller;


import com.example.berkay.Flight.entity.Flight;
import com.example.berkay.Flight.entity.FlightWithSeats;
import com.example.berkay.Flight.entity.Payment;
import com.example.berkay.Flight.entity.Seat;
import com.example.berkay.Flight.repository.FlightRepository;
import com.example.berkay.Flight.repository.PaymentRepository;
import com.example.berkay.Flight.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SeatController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private PaymentRepository paymentRepository;


    //Endpoint to list seats for a specific flight
    @GetMapping("/flights/{flightId}/seats")
    public ResponseEntity<List<Seat>> getAllSeatsByFlightId(@PathVariable(value = "flightId") Long flightId){

        if(!flightRepository.existsById(flightId)){
            throw new FlightNotFoundException(flightId);
        }

        List<Seat> seats = seatRepository.findByFlightId(flightId);
        return new ResponseEntity<>(seats, HttpStatus.OK);


    }


    //Endpoint to search for flights based on the departure city.
    @GetMapping("/search")
    public ResponseEntity<List<FlightWithSeats>> searchFlightByDepartureCity(@RequestParam String departureCity){
        List<Flight> flights = flightRepository.findByDepartureCity(departureCity);

        if(flights.isEmpty()){

        }


        List<FlightWithSeats> result = new ArrayList<>();

        for(Flight flight : flights){
            List<Seat> seats = seatRepository.findByFlightId(flight.getId());

            FlightWithSeats flightWithSeats = new FlightWithSeats(flight , seats);
            result.add(flightWithSeats);

        }

        return ResponseEntity.ok(result);
    }




    // Endpoint to create a seat and link it to a flight
    @PostMapping("/create_seat/{flightId}")
    public ResponseEntity<String> createAndLinkSeatToFlight(@RequestBody Seat seat,@PathVariable Long flightId) {
        Optional<Flight> flightOptional = flightRepository.findById(flightId);

        if (flightOptional.isPresent()) {
            Flight flight = flightOptional.get();
            seat.setFlight(flight);
            seatRepository.save(seat);

            return ResponseEntity.status(HttpStatus.CREATED).body("Seat linked to the flight.");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    //Endpoint to update a seat.
    @PutMapping("/seats/{id}")
    Seat replaceSeat(@RequestBody Seat newSeat, @PathVariable Long id) {

        return seatRepository.findById(id)
                .map(seat -> {
                    seat.setPrice(newSeat.getPrice());
                    seat.setSeatNumber((newSeat.getSeatNumber()));
                    seat.setBaggageCapacity(newSeat.getBaggageCapacity());


                    return seatRepository.save(seat);
                })
                .orElseGet(() -> {
                    newSeat.setId(id);
                    return seatRepository.save(newSeat);
                });
    }


    //Endpoint to delete a seat.
    @DeleteMapping("seats/{id}")
    void deleteSeat(@PathVariable Long id){
        seatRepository.deleteById(id);
    }


    //Endpoint to buy a selected seat
    @PostMapping("buy/{seatId}")
    public ResponseEntity<String> buySeat(@PathVariable Long seatId){
        Optional <Seat> seatOptional = seatRepository.findById(seatId);
        if(seatOptional.isPresent()){
            Seat seat = seatOptional.get();
            if(seat.isBought() == false){
                Payment payment = new Payment();
                payment.setPrice(seat.getPrice());
                payment.setBankResponse("Seat bought succesfully");
                paymentRepository.save(payment);
                seat.setBought(true);
                seat.setPayment(payment);
                seatRepository.save(seat);
                return ResponseEntity.status(HttpStatus.CREATED).body("Seat bought succesfully");

            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The seat you are trying to buy has already been purchased");
            }
        }

        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requested seat does not exist , please enter a valid seat id.");
        }
    }



}