package com.example.berkay.Flight.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Seat {

    @Id
    @GeneratedValue
    private Long id;

    private int baggageCapacity;
    private BigDecimal price;
    private int seatNumber;
    private boolean bought;






    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public int getBaggageCapacity(){
        return baggageCapacity;
    }
    public void setBaggageCapacity(int baggageCapacity){
        this.baggageCapacity = baggageCapacity;
    }

    public int getSeatNumber(){
        return seatNumber;
    }
    public void setSeatNumber(int seatNumber){
        this.seatNumber = seatNumber;


    }

    public BigDecimal getPrice(){
        return price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }


    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public boolean isBought(){
        return bought;
    }
    public void setBought(boolean bought){
        this.bought = bought;
    }

    public Payment getPayment(){
        return payment;

    }

    public void setPayment(Payment payment){
        this.payment = payment;
    }

    @OneToOne
    private Payment payment;

    @ManyToOne
    private Flight flight;


}