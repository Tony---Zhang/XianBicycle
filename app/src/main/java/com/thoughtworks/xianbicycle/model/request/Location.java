package com.thoughtworks.xianbicycle.model.request;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("lat")
    private double latitude;
    @SerializedName("lng")
    private double longitude;
    private double distance;

    public Location(double latitude, double longitude, double distance) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
