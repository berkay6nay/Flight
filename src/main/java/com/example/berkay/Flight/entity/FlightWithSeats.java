package com.example.berkay.Flight.entity;

import java.util.List;

public class FlightWithSeats {


    private Flight flight;
    private List<Seat> seats;

    public FlightWithSeats(Flight flight, List<Seat> seats) {
        this.flight = flight;
        this.seats = seats;
    }


    public Flight getFlight() {
        return flight;
    }


    public void setFlight(Flight flight) {
        this.flight = flight;
    }


    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

}