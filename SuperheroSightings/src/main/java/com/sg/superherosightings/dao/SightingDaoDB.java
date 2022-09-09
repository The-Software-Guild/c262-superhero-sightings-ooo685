package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SightingDaoDB implements SightingDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Sighting getSightingById(int id) {
        return null;
    }

    @Override
    public List<Sighting> getAllSightings() {
        return null;
    }

    @Override
    public Sighting addSighting(Sighting sighting) {
        return null;
    }

    @Override
    public void updateSighting(Sighting sighting) {

    }

    @Override
    public void deleteSightingById(int id) {

    }
}
