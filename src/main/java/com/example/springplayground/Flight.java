package com.example.springplayground;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Flight {
    private Date departsOn;
    private List<Ticket> tickets;

    Flight(Ticket ticket, Date date) {

        setTickets(Arrays.asList(ticket));
        setDepartsOn(date);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Tickets")
    public List<Ticket> getTickets() {

        return tickets;
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Departs")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date getDepartsOn() { return departsOn; }

    public void setDepartsOn(Date dateTime) { this.departsOn = dateTime; }

    public void setTickets(List<Ticket> tickets) {

        this.tickets = tickets;
    }

}
