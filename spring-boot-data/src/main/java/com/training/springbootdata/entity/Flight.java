package com.training.springbootdata.entity;


@Entity
@Table(name = "Flights")
public class Flight {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="flightID")
    private int flightId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="origin")
    private Airport origin;

    @Column(name="FlightNum")
    private String flightNum;
    @Column(name="Distance")
    private int distance;
    @Column(name="Dest")
    private String dest;
    @Column(name="AirTime")
    private int airTime;

    public int getFlightId() { return flightId; }

    public String getFlightNum() { return flightNum; }

    public int getDistance() {
        return distance;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getDest() {return dest;}

    public void setDest(String dest){ this.dest = dest;}

    public int getAirTime() {return airTime;}

    public void setAirTime(int airTime){ this.airTime = airTime;}

    public void setDistance(int distance) { this.distance = distance; }

}
