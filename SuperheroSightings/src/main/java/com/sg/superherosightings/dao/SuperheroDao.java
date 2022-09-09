package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.Superhero;

import java.util.List;

public interface SuperheroDao {

    Superhero getHeroById(int id);
    List<Superhero> getAllHeroes();
    Superhero addHero(Superhero superhero);
    void updateHero(Superhero superhero);
    void deleteHeroById(int id);

}
