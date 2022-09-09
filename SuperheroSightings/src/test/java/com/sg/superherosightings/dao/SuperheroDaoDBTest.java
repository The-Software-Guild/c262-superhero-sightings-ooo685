package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.entities.Superhero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class SuperheroDaoDBTest {

    @Autowired
    SuperheroDao superheroDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SightingDao sightingDao;

    public SuperheroDaoDBTest(){
    }

    @BeforeEach
    public void setUp(){
        List<Superhero> heroes = superheroDao.getAllHeroes();
        for(Superhero superhero : heroes){
            superheroDao.deleteHeroById(superhero.getId());
        }

        List<Organization> orgs = organizationDao.getAllOrgs();
        for(Organization org : orgs){
            organizationDao.deleteOrgById(org.getId());
        }

        List<Location> locations = locationDao.getAllLocations();
        for(Location location : locations){
            locationDao.deleteLocationById(location.getId());
        }

        List<Sighting> sightings = sightingDao.getAllSightings();
        for(Sighting sighting : sightings){
            sightingDao.deleteSightingById(sighting.getId());
        }
    }

    @Test
    public void testAddAndGetHero() {
        Superhero hero = new Superhero();
        hero.setName("Frozone");
        hero.setPower("Ice Bender");
        hero = superheroDao.addHero(hero);

        Superhero fromDao = superheroDao.getHeroById(hero.getId());
        assertEquals(hero, fromDao);
    }

    @Test
    public void testGetAllHeroes() {
        Superhero hero = new Superhero();
        hero.setName("Frozone");
        hero.setPower("Ice Bender");
        hero = superheroDao.addHero(hero);

        Superhero hero2 = new Superhero();
        hero2.setName("ElastiGirl");
        hero2.setPower("Extreme Flexibility");
        hero2 = superheroDao.addHero(hero2);

        List<Superhero> heroes = superheroDao.getAllHeroes();

        assertEquals(2, heroes.size());
        assertTrue(heroes.contains(hero));
        assertTrue(heroes.contains(hero2));
    }

    @Test
    public void testUpdateHero() {
        Superhero hero = new Superhero();
        hero.setName("Frozone");
        hero.setPower("Ice Bender");
        hero = superheroDao.addHero(hero);

        Superhero fromDao = superheroDao.getHeroById(hero.getId());
        assertEquals(hero, fromDao);

        hero.setName("Iceman");
        superheroDao.updateHero(hero);

        assertNotEquals(hero, fromDao);

        fromDao = superheroDao.getHeroById(hero.getId());

        assertEquals(hero, fromDao);
    }

    @Test
    public void testDeleteHeroById() {
        Superhero hero = new Superhero();
        hero.setName("Elastigirl");
        hero.setPower("Extreme Flexibility");
        hero = superheroDao.addHero(hero);
        List<Superhero> heroes = new ArrayList<>();
        heroes.add(hero);

        Organization org = new Organization();
        org.setName("The Incredibles");
        org.setAddress("1444 Incredible Way, Municiberg");
        org.setMembers(heroes);
        org = organizationDao.addOrg(org);

        Location location = new Location();
        location.setName("Eiffel Tower");
        location.setAddress("Paris");
        location.setLatitude(3200);
        location.setLongitude(7682);
        location = locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setSuperhero(hero);
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);

        Superhero fromDao = superheroDao.getHeroById(hero.getId());
        assertEquals(hero, fromDao);

        superheroDao.deleteHeroById(hero.getId());

        fromDao = superheroDao.getHeroById(hero.getId());
        assertNull(fromDao);
        //assertNull(sighting.getSuperhero());
        //assertNull(org.getMembers());
    }

}
