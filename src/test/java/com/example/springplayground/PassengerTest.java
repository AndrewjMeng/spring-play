package com.example.springplayground;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PassengerTest {
    @Test
    public void FirstNameTest() {
        Passenger passenger = new Passenger("bob", "smith");
        assertEquals("bob", passenger.getFirstName());
    }
    @Test
    public void LastNameTest() {
        Passenger passenger = new Passenger("bob", "smith");
        assertEquals("smith", passenger.getLastName());
    }
}
