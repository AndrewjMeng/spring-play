package com.example.springplayground;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class ControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void flightsFlights404POSTEndpoint() throws Exception {
        this.mvc.perform(post("/flights/flight")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void flightsFlightsEndpoint() throws Exception {
        this.mvc.perform(get("/flights/flight")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Departs", is("2014-06-08 05:00")))
                .andExpect(jsonPath("$.Tickets[0].Price", is(200)));
    }

    @Test
    public void Flights404POSTEndpoint() throws Exception {
        this.mvc.perform(post("/flights")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void FlightsEndpoint() throws Exception {
        this.mvc.perform(get("/flights")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Departs", is("2014-06-08 05:00")))
                .andExpect(jsonPath("$[0].Tickets[0].Price", is(400)))
                .andExpect(jsonPath("$[1].Departs", is("2014-06-08 05:00")))
                .andExpect(jsonPath("$[1].Tickets[0].Price", is(200)));
    }

    @Test
    public void FlightsIgnoreLastNameEndpoint() throws Exception {
        this.mvc.perform(get("/flights")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName", is("bob")));
    }
}
