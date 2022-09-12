package com.galvanize.flight.demo;

public class Tickets {
    int price;
    Passenger passenger;

    //for every field you need a getter and setter so ticket needs the passengers constructor

    public Tickets (int price, Passenger passenger) {
        this.price = price;
        this.passenger = passenger;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
