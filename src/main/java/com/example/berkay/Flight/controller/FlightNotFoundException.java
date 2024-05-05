package com.example.berkay.Flight.controller;

public class FlightNotFoundException extends RuntimeException {

    FlightNotFoundException(Long id){
        super("Could not find flight " + id);
    }

}