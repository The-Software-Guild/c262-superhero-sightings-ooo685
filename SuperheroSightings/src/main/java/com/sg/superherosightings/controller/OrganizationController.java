package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.dao.SuperheroDao;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Superhero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrganizationController {

    @Autowired
    SuperheroDao superheroDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SightingDao sightingDao;

    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> organizations = organizationDao.getAllOrgs();
        List<Superhero> superheroes = superheroDao.getAllHeroes();
        model.addAttribute("organizations", organizations);
        model.addAttribute("superheroes", superheroes);
        return "organizations";
    }

    @PostMapping("addOrganization")
    public String addOrganization(Organization organization, HttpServletRequest request) {
        String[] superheroIds = request.getParameterValues("superId");

        List<Superhero> superheroes = new ArrayList<>();
        for(String superheroId : superheroIds) {
            superheroes.add(superheroDao.getHeroById(Integer.parseInt(superheroId)));
        }
        organization.setMembers(superheroes);
        organizationDao.addOrg(organization);

        return "redirect:/organizations";
        //how bout address and description and name bye
    }

    @GetMapping("organizationDetail")
    public String organizationDetail(Integer id, Model model) {
        Organization organization = organizationDao.getOrgById(id);
        model.addAttribute("organization", organization);
        return "organizationDetail";
    }

    @GetMapping("deleteOrganization")
    public String deleteOrganization(Integer id) {
        organizationDao.deleteOrgById(id);
        return "redirect:/organizations";
    }

    @GetMapping("editOrganization")
    public String editOrganization(Integer id, Model model) {
        Organization organization = organizationDao.getOrgById(id);
        List<Superhero> superheroes = superheroDao.getAllHeroes();
        model.addAttribute("organization", organization);
        model.addAttribute("superheroes", superheroes);
        return "editOrganization";
    }

    @PostMapping("editOrganization")
    public String performEditOrganization(@Valid Organization organization, BindingResult result, HttpServletRequest request, Model model) {
        String[] superheroIds = request.getParameterValues("superId");

        List<Superhero> superheroes = new ArrayList<>();
        if(superheroIds != null) {
            for(String superheroId : superheroIds) {
                superheroes.add(superheroDao.getHeroById(Integer.parseInt(superheroId)));
            }
        } else {
            FieldError error = new FieldError("organization", "superheroes", "Must include one superhero");
            result.addError(error);
        }

        organization.setMembers(superheroes);

        if(result.hasErrors()) {
            model.addAttribute("superheroes", superheroDao.getAllHeroes());
            model.addAttribute("organization", organization);
            return "editOrganization";
        }

        organizationDao.updateOrg(organization);

        return "redirect:/organizations";
    }


}
