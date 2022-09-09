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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SightingDaoDBTest {

    @Autowired
    SuperheroDao superheroDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SightingDao sightingDao;

    public SightingDaoDBTest(){
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
    public void testAddAndGetSighting() {
        Superhero hero = new Superhero();
        hero.setName("Elastigirl");
        hero.setPower("Extreme Flexibility");
        hero = superheroDao.addHero(hero);

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

        Sighting fromDao = sightingDao.getSightingById(sighting.getId());
        assertEquals(sighting, fromDao);
    }

    @Test
    public void testGetAllSightings() {
        Superhero hero = new Superhero();
        hero.setName("Elastigirl");
        hero.setPower("Extreme Flexibility");
        hero = superheroDao.addHero(hero);

        Superhero hero2 = new Superhero();
        hero2.setName("Frozone");
        hero2.setPower("Ice Bender");
        hero2 = superheroDao.addHero(hero2);

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

        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.now());
        sighting2.setSuperhero(hero2);
        sighting2.setLocation(location);
        sighting2 = sightingDao.addSighting(sighting2);

        List<Sighting> sightings = sightingDao.getAllSightings();

        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));
    }

    @Test
    public void testUpdateSighting() {
        Superhero hero = new Superhero();
        hero.setName("Elastigirl");
        hero.setPower("Extreme Flexibility");
        hero = superheroDao.addHero(hero);

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

        Sighting fromDao = sightingDao.getSightingById(sighting.getId());
        assertEquals(sighting, fromDao);

        sighting.setDate(LocalDate.of(2000,2,9));
        sightingDao.updateSighting(sighting);

        assertNotEquals(sighting, fromDao);

        fromDao = sightingDao.getSightingById(sighting.getId());

        assertEquals(sighting, fromDao);
    }

    @Test
    public void testDeleteSightingById() {
        Superhero hero = new Superhero();
        hero.setName("Elastigirl");
        hero.setPower("Extreme Flexibility");
        hero = superheroDao.addHero(hero);

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

        Sighting fromDao = sightingDao.getSightingById(sighting.getId());
        assertEquals(sighting, fromDao);

        sightingDao.deleteSightingById(sighting.getId());

        fromDao = sightingDao.getSightingById(sighting.getId());
        assertNull(fromDao);
    }

}
