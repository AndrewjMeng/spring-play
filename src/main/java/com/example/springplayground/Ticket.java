package com.example.springplayground;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Ticket {
    private int price;
    private Passenger passenger;


    Ticket(Passenger passenger, int price){
        this.passenger = passenger;
        this.price = price;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Price")
    public int getPrice() {
        return price;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Passenger")
    public Passenger getPassenger() {

        return passenger;
    }

}