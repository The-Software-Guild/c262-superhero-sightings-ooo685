package com.sg.superherosightings.entities;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

public class Sighting {

    private int id;
    private Superhero superhero;
    private Location location;
    @NotBlank(message = "Date must not be empty")
    private LocalDate date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Superhero getSuperhero() {
        return superhero;
    }

    public void setSuperhero(Superhero superhero) {
        this.superhero = superhero;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sighting)) return false;
        Sighting sighting = (Sighting) o;
        return getId() == sighting.getId() && getSuperhero().equals(sighting.getSuperhero()) && getLocation().equals(sighting.getLocation()) && getDate().equals(sighting.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSuperhero(), getLocation(), getDate());
    }
}
