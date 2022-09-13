package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.dao.SuperheroDao;
import com.sg.superherosightings.entities.Superhero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SuperheroController {

    @Autowired
    SuperheroDao superheroDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SightingDao sightingDao;

    @GetMapping("superheroes")
    public String displayHeroes(Model model) {
        List<Superhero> heroes = superheroDao.getAllHeroes();
        model.addAttribute("superheroes", heroes);
        return "superheroes";
    }

    @PostMapping("addSuperhero")
    public String addHero(String name, String power, String description) {
        Superhero superhero = new Superhero();
        superhero.setName(name);
        superhero.setPower(power);
        superhero.setDescription(description);
        superheroDao.addHero(superhero);

        return "redirect:/superheroes";
    }

    @GetMapping("deleteSuperhero")
    public String deleteHero(Integer id) {
        superheroDao.deleteHeroById(id);
        return "redirect:/superheroes";
    }

    @GetMapping("editSuperhero")
    public String editStudent(Integer id, Model model) {
        Superhero superhero = superheroDao.getHeroById(id);
        model.addAttribute("superhero", superhero);
        return "editSuperhero";
    }

    @PostMapping("editSuperhero")
    public String performEditStudent(@Valid Superhero superhero, BindingResult result) {
        if(result.hasErrors()) {
            return "editSuperhero";
        }
        superheroDao.updateHero(superhero);
        return "redirect:/superheroes";
    }

}
