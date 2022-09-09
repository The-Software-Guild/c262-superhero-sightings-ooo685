package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.Sighting;

import java.util.List;

public interface SightingDao {

    Sighting getSightingById(int id);
    List<Sighting> getAllSightings();
    Sighting addSighting(Sighting sighting);
    void updateSighting(Sighting sighting);
    void deleteSightingById(int id);

}
