package com.sg.superherosightings.entities;

import java.util.List;
import java.util.Objects;

public class Organization {

    private int id;
    private String name;
    private String address;
    private String description;
    private List<Superhero> members;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Superhero> getMembers() {
        return members;
    }

    public void setMembers(List<Superhero> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return getId() == that.getId() && getName().equals(that.getName()) && getAddress().equals(that.getAddress()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getMembers(), that.getMembers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getDescription(), getMembers());
    }
}
