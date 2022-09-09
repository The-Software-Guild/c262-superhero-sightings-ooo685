package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.Superhero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SuperheroDaoDB implements SuperheroDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Superhero getHeroById(int id) {
        return null;
    }

    @Override
    public List<Superhero> getAllHeroes() {
        return null;
    }

    @Override
    public Superhero addHero(Superhero superhero) {
        return null;
    }

    @Override
    public void updateHero(Superhero superhero) {

    }

    @Override
    public void deleteHeroById(int id) {

    }
}
