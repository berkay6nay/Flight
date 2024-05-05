Flight Booking System Documentation

Tools used : Java and Spring Boot

Entities : Flight , Seat , Payment

Flight Endpoints:

1-> /flights endpoint:
    
    This get request returns all the flights in the db.

2-> /flights/{id} endpoint:
    
    This get request returns the specific flight of the id.

3-> /flights endpoint:
    
    This post request creates a flight.A sample json file to send would be :
    POST /flights
    {
    "date": "2023-09-22",
    "departureCity": "Istanbul",
    "landingCity": "Amsterdam",
    "departingTime": "10:30 AM",
    "flightName": "FL567"
    }

4-> /flights/{id} endpoint:
    
    This put request updates a flight.A sample json file to send would be :
    PUT /flights/3
    {
    "date": "2023-09-22",
    "departureCity": "Ankara",
    "landingCity": "Amsterdam",
    "departingTime": "10:30 AM",
    "flightName": "FL567"
    }

5-> /flights/{id} endpoint : 
    
    This delete request deletes the specified flight.

Seat Endpoints:

1-> /flights/{flightId}/seats endpoint:
    
    This get request returns the seats of the specified flight.
    A sample query would be :
    /flights/1/seats

2-> /search?departureCity=YourCity endpoint
    
    This get request returns the flights with the specified departureCity
    A sample query would be /search?departureCity=Istanbul

3-> /create_seat/{flightId} endpoint :
    
    This post request creates a seat for the specified flight:
    A sample json file would be :
    POST /create_seat/5
    {
    "baggageCapacity" : 25,
    "price" : 150,
    "seatNumber" : 1
    }

4-> /seats/{id} endpoint:
    
    This put request updates the specified seat.A sample query would be :

    PUT /seats/1
    {
    "baggageCapacity" : 25,
    "price" : 150,
    "seatNumber" : 2
    }

5-> seats/{id} endpoint:
    
    This delete request deletes the specified seat

6-> buy/{seatId} endpoint:
    
    This post request buys the seat , changes its state to bought and creates a payment object associated to it.

Resources I have used while writing this programm:

https://spring.io/projects/spring-boot
https://spring.io/guides/tutorials/rest/
https://www.youtube.com/watch?v=8qhaDBCJh6I
https://www.baeldung.com/spring-data-rest-relationships
https://www.bezkoder.com/jpa-one-to-many/
https://github.com/gencdevops/2022-Fullstack-Bootcamp-1/





