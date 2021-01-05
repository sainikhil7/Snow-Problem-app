package com.example.slip_admin;

public class Artist {
    String requestId;
    String cost;

    String location;
    String coordinates;
    String cars;
    String time;
    public Artist(){

    }

    public Artist(String requestId, String cost, String location, String coordinates, String cars, String time) {
        this.requestId = requestId;
        this.cost = cost;
        this.location = location;
        this.coordinates = coordinates;
        this.cars = cars;
        this.time = time;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getCost() {
        return cost;
    }

    public String getLocation() {
        return location;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public String getCars() {
        return cars;
    }

    public String getTime() {
        return time;
    }
/***

    public Artist(String requestId, String cost, String location, String coordinates,String cars, String time) {
        this.requestId = RequestId;
        this.Cost = Cost;

        this.Location = Location;
        this.Coordinates = Coordinates;
        this.Cars = Cars;
        this.Time = Time;

    }


    public String getRequestId() {
        return RequestId;
    }

    public String getCost() {
        return Cost;
    }






    public String getLocation() {
        return Location;
    }


    public String getCoordinates() {
        return Coordinates;
    }

    public String getCars() {
        return Cars;
    }

    public String getTime() {
        return Time;
    }
     ***/
}
