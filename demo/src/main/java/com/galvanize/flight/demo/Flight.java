package com.galvanize.flight.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight {
    //departs and ticket list is the square
    LocalDateTime departsOn;
    ArrayList<Tickets> tickets;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime getDepartsOn() {
        return departsOn;
    }

    public void setDepartsOn(LocalDateTime dateTime) {
        this.departsOn = dateTime;
    }
@JsonProperty("Tickets")
    public ArrayList<Tickets> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Tickets> tickets) {
        this.tickets = tickets;
    }

    public int totalTicketsSum(){
        int ticketSum = 0;
        for (int i = 0; i < tickets.size(); i++) {
            ticketSum += tickets.get(i).price;
        }
        return ticketSum;
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
