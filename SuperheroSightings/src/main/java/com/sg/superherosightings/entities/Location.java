package com.sg.superherosightings.entities;

import java.util.Objects;

public class Location {

    private int id;
    private String name;
    private String address;
    private int latitude;
    private int longitude;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return getId() == location.getId() && getLatitude() == location.getLatitude() && getLongitude() == location.getLongitude() && getName().equals(location.getName()) && getAddress().equals(location.getAddress()) && Objects.equals(getDescription(), location.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getLatitude(), getLongitude(), getDescription());
    }
}
