package com.superhero.Superhero.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superhero.Superhero.annotation.ExecutionTime;
import com.superhero.Superhero.exception.EntityNotFoundException;
import com.superhero.Superhero.model.Superhero;
import com.superhero.Superhero.repository.SuperheroRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@Service
public class SuperheroService {

    @Autowired
    private SuperheroRepository superheroRepository;

    @ExecutionTime
    @Cacheable("superheroes")
    public List<Superhero> getAllSuperheroes() {
        return superheroRepository.findAll();
    }


    @ExecutionTime
    @Cacheable(value = "superheroes", key = "#name")
    public List<Superhero> getSuperheroesByName(String name) {
        try{
            return superheroRepository.findByNameContainingIgnoreCase(name);
        }catch(Exception e){
            throw new RuntimeException("Error during superhero search by name: " + e.getMessage(), e);        
        }
    }


    @ExecutionTime
    @CacheEvict(value = "superheroes")
    public Superhero createSuperhero(Superhero superhero) {
        try {
            return superheroRepository.save(superhero);
        } catch (Exception e) {
            throw new RuntimeException("Error creating superhero: " + e.getMessage(), e);
        }
    }

    @ExecutionTime
    @CacheEvict(value = "superheroes")
    public Superhero updateSuperhero(Long id, Superhero updatedSuperhero) {

        Optional<Superhero> optionalSuperhero = superheroRepository.findById(id);

        if (optionalSuperhero.isPresent()) {
            Superhero superhero = optionalSuperhero.get();
            superhero.setName(updatedSuperhero.getName());
            return superheroRepository.save(superhero);
        } else {
            throw new EntityNotFoundException("Superhero not found with id: " + id);
        }
    }

    @ExecutionTime
    @CacheEvict(value = "superheroes")
    public Optional<Superhero> getSuperheroesById(Long id) {
        if (superheroRepository.existsById(id)) {
            return superheroRepository.findById(id);
        }else{
            throw new EntityNotFoundException("Superhero not found with id: " + id);
        }
    }

    @ExecutionTime
    @CacheEvict(value = "superheroes", allEntries = true)
    public void deleteSuperhero(Long id) {

        if (superheroRepository.existsById(id)) {
            superheroRepository.deleteById(id);            
        } else {
            throw new EntityNotFoundException("Superhero not found with id: " + id);
        }
    }

}