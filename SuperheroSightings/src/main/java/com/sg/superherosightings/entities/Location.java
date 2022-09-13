package com.sg.superherosightings.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Location {

    private int id;
    @NotBlank(message = "Name must not be empty")
    @Size(max = 50, message = "Name must be less than 50 characters")
    private String name;
    @NotBlank(message = "Address must not be empty")
    @Size(max = 200, message = "Address must be less than 200 characters")
    private String address;
    @NotBlank(message = "Latitude must not be empty")
    private int latitude;
    @NotBlank(message = "Longitude must not be empty")
    private int longitude;
    @Size(max = 250, message = "Description must be less than 250 characters")
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
