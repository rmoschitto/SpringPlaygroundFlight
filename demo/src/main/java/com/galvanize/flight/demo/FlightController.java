package com.galvanize.flight.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class FlightController {

    @GetMapping("/flights/flight")
    public Flight getFlight() {
        Flight flight1 = new Flight();
        flight1.setDepartsOn(LocalDateTime.of(2017, 4, 21, 14, 34));
        Passenger passenger1 = new Passenger("Some name", "Some other name");
        Tickets tickets1 = new Tickets(200, passenger1);
        List<Tickets> tickets = new ArrayList<>();  //created new List of ticket objects called tickets
        tickets.add(tickets1);  //added tickets1 to list
        flight1.setTickets(tickets);

        return flight1;
    }

    @GetMapping("/flights")
    public List<Flight> getFlights() {
        Flight flight1 = new Flight();
        flight1.setDepartsOn(LocalDateTime.of(2017, 4, 21, 14, 34));
        Passenger passenger1 = new Passenger("Dwayne", "Johnson");
        Tickets tickets1 = new Tickets(200, passenger1);
        List<Tickets> tickets = new ArrayList<>();  //created new List of ticket objects called tickets
        tickets.add(tickets1);  //added tickets1 to list
        flight1.setTickets(tickets);

        Flight flight2 = new Flight();
        flight2.setDepartsOn(LocalDateTime.of(2017, 4, 21, 14, 34));
        Passenger passenger2 = new Passenger("Cher", "");
        Tickets tickets2 = new Tickets(400, passenger2);
        tickets.add(tickets2);  //added tickets1 to list
        flight2.setTickets(tickets);

        return Arrays.asList(flight1, flight2);
    }

    @PostMapping("/flights/tickets/total")
    public List<Flight> getFlightsSum() {
    }

    }
