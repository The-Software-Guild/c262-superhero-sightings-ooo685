package com.sg.superherosightings.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class Organization {

    private int id;
    @NotBlank(message = "Name must not be empty")
    @Size(max = 50, message = "Name must be less than 50 characters")
    private String name;
    @NotBlank(message = "Address must not be empty")
    @Size(max = 200, message = "Address must be less than 200 characters")
    private String address;
    @Size(max = 250, message = "Description must be less than 250 characters")
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
