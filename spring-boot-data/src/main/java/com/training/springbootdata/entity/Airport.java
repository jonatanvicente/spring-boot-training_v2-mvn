package com.training.springbootdata.entity;


import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "USAirports")
public class Airport {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="IATA")
    private String iata;
    @Column(name="Airport")
    private String airport;
    @Column(name="City")
    private String city;
    @Column(name="State")
    private String state;

    @OneToMany(mappedBy = "origin",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flight> flights;

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public String getIata() {
        return iata;
    }

    public String getAirport() {
        return airport;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

}
