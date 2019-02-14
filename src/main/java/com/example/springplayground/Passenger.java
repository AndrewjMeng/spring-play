package com.example.springplayground;

import com.fasterxml.jackson.annotation.*;

public class Passenger {

    private String firstName;


    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("FirstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("LastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    Passenger( String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

}
