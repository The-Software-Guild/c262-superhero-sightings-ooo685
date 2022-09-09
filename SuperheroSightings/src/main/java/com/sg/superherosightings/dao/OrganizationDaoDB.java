package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Superhero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizationDaoDB implements OrganizationDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Organization getOrgById(int id) {
        return null;
    }

    @Override
    public List<Organization> getAllOrgs() {
        return null;
    }

    @Override
    public Organization addOrg(Organization organization) {
        return null;
    }

    @Override
    public void updateOrg(Organization organization) {

    }

    @Override
    public void deleteOrgById(int id) {

    }

    @Override
    public List<Organization> getOrgsForSuperhero(Superhero superhero) {
        return null;
    }
}
