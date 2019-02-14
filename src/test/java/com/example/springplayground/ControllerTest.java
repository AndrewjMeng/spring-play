package com.example.springplayground;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.Matchers.is;

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

    @Test
    public void TicketTotal404GetEndpoint() throws Exception {
        this.mvc.perform(get("/flights/tickets/total")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }


    @Test
    public void TicketTotalPOSTEndpointJSONString() throws Exception {
        this.mvc.perform(post("/flights/tickets/total")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content("{\"tickets\":[{\"passenger\":{\"firstName\":\"Some name\",\"lastName\":\"Some other name\"},\"price\":200},{\"passenger\":{\"firstName\":\"Name B\",\"lastName\":\"Name C\"},\"price\":150}]}"))
                .andExpect(status().isOk()).andExpect(content().string("{\"result\":350}"));
    }

    @Test
    public void TicketTotalPOSTEndpointObjectMapper() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Total.Passenger passenger1 = new Total.Passenger();
        passenger1.setFirstName("bob");
        passenger1.setLastName("smith");
        Total.Ticket ticket1 = new Total.Ticket();
        ticket1.setPassenger(passenger1);
        ticket1.setPrice(150);
        Total.Ticket ticket2 = new Total.Ticket();
        ticket2.setPassenger(passenger1);
        ticket2.setPrice(200);

        HashMap<String, Object> data = new HashMap<String, Object>(){  // 2
            {
                put("tickets", Arrays.asList(ticket1,ticket2));
            }
        };

        String json = objectMapper.writeValueAsString(data);
        this.mvc.perform(post("/flights/tickets/total")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andExpect(content().string("{\"result\":350}"));
    }

    @Test
    public void TicketTotalPOSTEndpointFileFixtures() throws Exception {

        String json = getJSON("/testdata.json");
        this.mvc.perform(post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":350}"));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }

}
