package com.galvanize.flight.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tickets {
    int price;
    Passenger passenger;

    //for every field you need a getter and setter so ticket needs the passengers constructor


    public Tickets() {
    }

    public Tickets (int price, Passenger passenger) {
        this.price = price;
        this.passenger = passenger;
    }
    @JsonProperty("Price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    @JsonProperty("Passenger")
    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
