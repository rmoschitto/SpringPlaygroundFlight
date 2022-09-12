package com.galvanize.flight.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;

public class Flight {
    //departs and ticket list is the square
    LocalDateTime departsOn;
    List<Tickets> tickets;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime getDepartsOn() {
        return departsOn;
    }

    public void setDepartsOn(LocalDateTime dateTime) {
        this.departsOn = dateTime;
    }

    public List<Tickets> getTickets() {
        return tickets;
    }

    public void setTickets(List<Tickets> tickets) {
        this.tickets = tickets;
    }
}


//    private int id;
//    private String destination;

    //public int getId() {
    //        return id;
    //    }
    //
    //    public void setId(int id) {
    //        this.id = id;
    //    }
    //
    //    public String getDestination() {
    //        return destination;
    //    }
    //
    //    public void setDestination(String destination) {
    //        this.destination = destination;
    //    }
