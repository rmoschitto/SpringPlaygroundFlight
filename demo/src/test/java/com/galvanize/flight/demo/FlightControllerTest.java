package com.galvanize.flight.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlightController.class)

public class FlightControllerTest {

    @Autowired
    //ask why this is private?
    private MockMvc mvc;

    @Test
    public void getFlightInfoWorks() throws Exception {
        this.mvc.perform(
                        get("/flights/flight")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departsOn", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.tickets[0].passenger.firstName", is("Dwayne")))
                .andExpect(jsonPath("$.tickets[0].passenger.lastName", is("Johnson")))
                .andExpect(jsonPath("$.tickets[0].price", is(200)));
    }

    @Test
    public void getFlightsWorks() throws Exception {
        this.mvc.perform(
                        get("/flights")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].departsOn", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[0].tickets[0].passenger.firstName", is("Dwayne")))
                .andExpect(jsonPath("$[0].tickets[0].passenger.lastName", is("Johnson")))
                .andExpect(jsonPath("$[0].tickets[0].price", is(200)))

        //second flight
                .andExpect(jsonPath("$[1].departsOn", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[1].tickets[1].passenger.firstName", is("Cher")))
                .andExpect(jsonPath("$[1].tickets[1].passenger.lastName", is("")))
                .andExpect(jsonPath("$[1].tickets[1].price", is(400)));
    }
}
