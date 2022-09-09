package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Superhero;

import java.util.List;

public interface OrganizationDao {

    Organization getOrgById(int id);
    List<Organization> getAllOrgs();
    Organization addOrg(Organization organization);
    void updateOrg(Organization organization);
    void deleteOrgById(int id);
    List<Organization> getOrgsForSuperhero(Superhero superhero);

}
