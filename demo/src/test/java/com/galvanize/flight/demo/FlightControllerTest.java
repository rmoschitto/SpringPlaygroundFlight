package com.galvanize.flight.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                .andExpect(jsonPath("$.Tickets[0].Passenger.FirstName", is("Dwayne")))
                .andExpect(jsonPath("$.Tickets[0].Passenger.LastName", is("Johnson")))
                .andExpect(jsonPath("$.Tickets[0].Price", is(200)));
    }

    @Test
    public void getFlightsWorks() throws Exception {
        this.mvc.perform(
                        get("/flights")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].departsOn", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName", is("Dwayne")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.LastName", is("Johnson")))
                .andExpect(jsonPath("$[0].Tickets[0].Price", is(200)))

                //second flight
                .andExpect(jsonPath("$[1].departsOn", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[1].Tickets[1].Passenger.FirstName", is("Cher")))
                .andExpect(jsonPath("$[1].Tickets[1].Passenger.LastName", is("")))
                .andExpect(jsonPath("$[1].Tickets[1].Price", is(400)))
                .andExpect(jsonPath("$[1].Tickets[1].Passenger.LastName", is("")));
             // .andExpect(jsonPath("$[1].tickets[1].passenger.lastName", doesNotHaveJSONPATH))

    }

   @Test
   public void canCalculateTotalJSONString() throws Exception{
        MockHttpServletRequestBuilder request1 = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "Tickets": [
                            {
                              "Passenger": {
                                "FirstName": "Some name",
                                "LastName": "Some other name"
                              },
                              "Price": 200
                            },
                            {
                              "Passenger": {
                                "FirstName": "Name B",
                                "LastName": "Name C"
                              },
                              "Price": 150
                            }
                          ]
                        }""");

        this.mvc.perform(request1)
                .andExpect(jsonPath("$.result").value(350));
   }

    @Test
    public void canCalculateTotalObjectMapper() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        HashMap<String, List<Tickets>> data = new HashMap<>();
        Passenger passenger1 = new Passenger("Anthony", "Casper");
        Passenger passenger2 = new Passenger("Nik", "TheMan");
        Tickets tickets1 = new Tickets(200, passenger1);
        Tickets tickets2 = new Tickets(150, passenger2);
        List<Tickets> ticketList = new ArrayList<>(Arrays.asList(tickets1, tickets2));
        data.put("Tickets", ticketList);

        String json = objectMapper.writeValueAsString(data);
                MockHttpServletRequestBuilder request1 = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request1)
                .andExpect(jsonPath("$.result").value(350));
    }

    @Test
    public void setTicketSumWorks() throws Exception {
        String json = getJSON("/data.json");

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(350));
    }
    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }
}

