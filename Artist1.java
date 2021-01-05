package com.example.myapplication;

public class Artist {
    String RequestId;
    String Cost;

    String Location;
    String Coordinates;
    String Cars;
    String Time;
    public Artist(){

    }

    public Artist(String RequestId, String Cost, String Location, String Coordinates,String Cars, String Time) {
        this.RequestId = RequestId;
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
}
