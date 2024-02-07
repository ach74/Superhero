package com.superhero.Superhero.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.superhero.Superhero.annotation.ExecutionTime;
import com.superhero.Superhero.model.Superhero;
import com.superhero.Superhero.service.SuperheroService;


@RestController
@RequestMapping("/api/superheroes")
public class SuperheroController {

    @Autowired
    private SuperheroService superheroService;

    @ExecutionTime
    @GetMapping
    public List<Superhero> getAllSuperheroes() {
        return superheroService.getAllSuperheroes();
    }

    @ExecutionTime
    @GetMapping("/{id}")
    public Superhero getSuperhero(@PathVariable Long id) {
        return superheroService.getSuperheroesById(id).orElse(null);
    }

    @ExecutionTime
    @GetMapping("/search")
    public List<Superhero> getSuperheroesByName(@RequestParam String name) {
        return superheroService.getSuperheroesByName(name);
    }

    @ExecutionTime
    @PostMapping("/create")
    public Superhero createSuperhero(@RequestBody Superhero superhero) {
        return superheroService.createSuperhero(superhero);
    }

    @ExecutionTime
    @PutMapping("/update/{id}")
    public Superhero updateSuperhero(@PathVariable Long id, @RequestBody Superhero updatedSuperhero) {
        return superheroService.updateSuperhero(id, updatedSuperhero);
    }

    @ExecutionTime
    @DeleteMapping("/delete/{id}")
    public void deleteSuperhero(@PathVariable Long id){
        superheroService.deleteSuperhero(id);
    }

}
