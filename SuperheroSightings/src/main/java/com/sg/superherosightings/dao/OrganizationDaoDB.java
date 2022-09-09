package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Superhero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrganizationDaoDB implements OrganizationDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Organization getOrgById(int id) {
        try {
            final String SELECT_ORG_BY_ID = "SELECT * FROM organization WHERE id = ?";
            Organization org = jdbc.queryForObject(SELECT_ORG_BY_ID, new OrgMapper(), id);
            org.setMembers(getHeroesForOrg(id));
            return org;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    private List<Superhero> getHeroesForOrg(int id) {
        final String SELECT_HEROES_FOR_ORG = "SELECT s.* FROM superhero s "
                + "JOIN super_organization so ON so.superId = s.id WHERE so.orgId = ?";
        return jdbc.query(SELECT_HEROES_FOR_ORG, new SuperheroDaoDB.HeroMapper(), id);
    }

    @Override
    public List<Organization> getAllOrgs() {
        final String SELECT_ALL_ORGS = "SELECT * FROM organization";
        List<Organization> orgs = jdbc.query(SELECT_ALL_ORGS, new OrgMapper());
        associateHeroes(orgs);
        return orgs;
    }

    private void associateHeroes(List<Organization> orgs) {
        for (Organization org : orgs) {
            org.setMembers(getHeroesForOrg(org.getId()));
        }
    }

    @Override
    @Transactional
    public Organization addOrg(Organization organization) {
        final String INSERT_ORG = "INSERT INTO organization(name, description, address) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_ORG,
                organization.getName(),
                organization.getDescription(),
                organization.getAddress());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setId(newId);
        insertSuperOrg(organization);
        return organization;
    }

    private void insertSuperOrg(Organization organization) {
        final String INSERT_SUPER_ORG = "INSERT INTO "
                + "super_organization(orgId, superId) VALUES(?,?)";
        for(Superhero hero : organization.getMembers()) {
            jdbc.update(INSERT_SUPER_ORG,
                    organization.getId(),
                    hero.getId());
        }
    }

    @Override
    @Transactional
    public void updateOrg(Organization organization) {
        final String UPDATE_ORG = "UPDATE organization SET name = ?, description = ?, address= ? WHERE id = ?";
        jdbc.update(UPDATE_ORG,
                organization.getName(),
                organization.getDescription(),
                organization.getAddress(),
                organization.getId());

        final String DELETE_SUPER_ORG = "DELETE FROM super_organization WHERE orgId = ?";
        jdbc.update(DELETE_SUPER_ORG, organization.getId());
        insertSuperOrg(organization);
    }

    @Override
    @Transactional
    public void deleteOrgById(int id) {
        final String DELETE_SUPER_ORG = "DELETE FROM super_organization WHERE orgId = ?";
        jdbc.update(DELETE_SUPER_ORG, id);

        final String DELETE_ORG = "DELETE FROM organization WHERE id = ?";
        jdbc.update(DELETE_ORG, id);
    }

    @Override
    public List<Organization> getOrgsForSuperhero(Superhero superhero) {
        final String SELECT_ORGS_FOR_HERO = "SELECT o.* FROM organization o JOIN "
                + "super_organization so ON so.orgId = o.Id WHERE so.superId = ?";
        List<Organization> orgs = jdbc.query(SELECT_ORGS_FOR_HERO,
                new OrgMapper(), superhero.getId());
        associateHeroes(orgs);
        return orgs;
    }

    public static final class OrgMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization org = new Organization();
            org.setId(rs.getInt("id"));
            org.setName(rs.getString("name"));
            org.setDescription(rs.getString("description"));
            org.setAddress(rs.getString("address"));
            return org;
        }
    }
}
