package com.example.berkay.Flight.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Flight{
    @Id
    @GeneratedValue

    private Long id;
    private String date;
    private String departureCity;
    private String landingCity;
    private String flightName;
    private String departingTime;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }





    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }


    public String getDepatureCity(){
        return departureCity;
    }
    public void setDepartureCity(String departureCity){
        this.departureCity = departureCity;
    }


    public String getLandingCity(){
        return landingCity;
    }
    public void setLandingCity(String landingCity){
        this.landingCity = landingCity;
    }



    public String getDepartingTime(){
        return departingTime;
    }
    public void setDepartingTime(String departingTime){
        this.departingTime = departingTime;
    }

    public String getFlightName(){
        return flightName;
    }
    public void setFlightName(String flightName){
        this.flightName = flightName;
    }

    @OneToMany(mappedBy = "flight" , cascade = CascadeType.ALL)
    private List<Seat> seats;




}
