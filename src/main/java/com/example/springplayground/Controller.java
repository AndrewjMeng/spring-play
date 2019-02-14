package com.example.springplayground;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@RestController
@RequestMapping("/")
public class Controller {
    @GetMapping("flights")
    public List<Flight> getFlights(){
        Passenger passenger1 = new Passenger("bob", "smith");
        Ticket ticket1 = new Ticket(passenger1, 400);
        Passenger passenger2 = new Passenger("bob", null);
        Ticket ticket2 = new Ticket(passenger2, 200);
        Flight flight1 = new Flight(ticket1, new Date(2014 - 1900, 5, 8));
        Flight flight2 = new Flight(ticket2, new Date(2014 - 1900, 5, 8));
        return Arrays.asList(flight1,flight2);
    }
    @GetMapping("flights/flight")
    public Flight getOneFlight(){
        Passenger passenger = new Passenger("bob", "smith");
        Ticket ticket = new Ticket(passenger, 200);
        Flight flight = new Flight(ticket, new Date(2014 - 1900, 5, 8));

        return flight;
    }

    @PostMapping("/flights/tickets/total")
    public Total.Result getTicketTotal(@RequestBody Total total){
        Total.Result result = new Total.Result(total.getTotal());

        return result;
    }
}
