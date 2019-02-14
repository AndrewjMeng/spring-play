package com.example.springplayground;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

public class Total {
    private Ticket[] tickets;

    public Ticket[] getTickets() {
        return tickets;
    }

    public int getTotal() {
        int total = 0;

        for(Ticket ticket: tickets) {
            total += ticket.getPrice();
        }

        return total;
    }



    public void setTickets(Ticket[] tickets) {
        this.tickets = tickets;
    }

    static class Ticket {
        private Passenger passenger;
        private int price;

        public Passenger getPassenger() {
            return passenger;
        }

        public void setPassenger(Passenger passenger) {
            this.passenger = passenger;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

    }
    static class Passenger{
        private String firstName;
        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
    static class Result {
        private int result;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }
        Result(int result){
            this.result = result;
        }
    }
}


//{
//        "tickets": [
//        {
//        "passenger": {
//        "firstName": "Some name",
//        "lastName": "Some other name"
//        },
//        "price": 200
//        },
//        {
//        "passenger": {
//        "firstName": "Name B",
//        "lastName": "Name C"
//        },
//        "price": 150
//        }
//        ]
//    }