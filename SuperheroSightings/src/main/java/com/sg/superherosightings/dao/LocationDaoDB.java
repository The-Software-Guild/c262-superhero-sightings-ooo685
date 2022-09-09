package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationDaoDB implements LocationDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Location getLocationById(int id) {
        return null;
    }

    @Override
    public List<Location> getAllLocations() {
        return null;
    }

    @Override
    public Location addLocation(Location location) {
        return null;
    }

    @Override
    public void updateLocation(Location location) {

    }

    @Override
    public void deleteLocationById(int id) {

    }
}
