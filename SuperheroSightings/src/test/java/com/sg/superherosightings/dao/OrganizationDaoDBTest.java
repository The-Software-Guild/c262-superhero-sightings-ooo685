package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.entities.Superhero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrganizationDaoDBTest {

    @Autowired
    SuperheroDao superheroDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SightingDao sightingDao;

    public OrganizationDaoDBTest(){
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
    public void testAddAndGetOrg() {
        Superhero hero = new Superhero();
        hero.setName("Frozone");
        hero.setPower("Ice Bender");
        hero = superheroDao.addHero(hero);

        List<Superhero> heroes = new ArrayList<>();
        heroes.add(hero);

        Organization org = new Organization();
        org.setName("The Incredibles");
        org.setAddress("1444 Incredible Way, Municiberg");
        org.setMembers(heroes);
        org = organizationDao.addOrg(org);

        Organization fromDao = organizationDao.getOrgById(org.getId());
        assertEquals(org, fromDao);
    }

    @Test
    public void testGetAllCourses() {
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

        Organization org2 = new Organization();
        org2.setName("Fake Org");
        org2.setAddress("Random Place");
        org2.setMembers(heroes);
        org2 = organizationDao.addOrg(org2);

        List<Organization> orgs = organizationDao.getAllOrgs();
        assertEquals(2, orgs.size());
        assertTrue(orgs.contains(org));
        assertTrue(orgs.contains(org2));
    }

    @Test
    public void testUpdateCourse() {
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

        Organization fromDao = organizationDao.getOrgById(org.getId());
        assertEquals(org, fromDao);

        org.setName("New Test Org Name");
        Superhero hero2 = new Superhero();
        hero2.setName("Frozone");
        hero2.setPower("Ice Bender");
        hero2 = superheroDao.addHero(hero2);
        heroes.add(hero2);
        org.setMembers(heroes);

        organizationDao.updateOrg(org);

        assertNotEquals(org, fromDao);

        fromDao = organizationDao.getOrgById(org.getId());
        assertEquals(org, fromDao);
    }

    @Test
    public void testDeleteCourseById() {
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

        Organization fromDao = organizationDao.getOrgById(org.getId());
        assertEquals(org, fromDao);

        organizationDao.deleteOrgById(org.getId());

        fromDao = organizationDao.getOrgById(org.getId());
        assertNull(fromDao);
    }

    @Test
    public void testGetCoursesForStudent() {
        Superhero hero = new Superhero();
        hero.setName("Frozone");
        hero.setPower("Ice Bender");
        hero = superheroDao.addHero(hero);

        Superhero hero2 = new Superhero();
        hero2.setName("ElastiGirl");
        hero2.setPower("Extreme Flexibility");
        hero2 = superheroDao.addHero(hero2);

        List<Superhero> heroes = new ArrayList<>();
        heroes.add(hero);
        heroes.add(hero2);

        List<Superhero> heroes2 = new ArrayList<>();
        heroes2.add(hero2);

        Organization org = new Organization();
        org.setName("The Incredibles");
        org.setAddress("1444 Incredible Way, Municiberg");
        org.setMembers(heroes);
        org = organizationDao.addOrg(org);

        Organization org2 = new Organization();
        org2.setName("New Name");
        org2.setAddress("Nowhere");
        org2.setMembers(heroes2);
        org2 = organizationDao.addOrg(org2);

        Organization org3 = new Organization();
        org3.setName("Toto");
        org3.setAddress("Africa");
        org3.setMembers(heroes);
        org3 = organizationDao.addOrg(org3);

        List<Organization> orgs = organizationDao.getOrgsForSuperhero(hero);
        assertEquals(2, orgs.size());
        assertTrue(orgs.contains(org));
        assertFalse(orgs.contains(org2));
        assertTrue(orgs.contains(org3));
    }

}
