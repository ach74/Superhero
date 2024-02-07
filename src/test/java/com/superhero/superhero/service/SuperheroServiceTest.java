package com.superhero.superhero.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.superhero.Superhero.model.Superhero;
import com.superhero.Superhero.repository.SuperheroRepository;
import com.superhero.Superhero.service.SuperheroService;

public class SuperheroServiceTest {

    @Mock
    private SuperheroRepository superheroRepository;

    @InjectMocks
    private SuperheroService superheroService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testGetAllSuperheroes() {
        // Arrange
        List<Superhero> superheroes = new ArrayList<>();
        superheroes.add(new Superhero(1L, "Spiderman"));
        superheroes.add(new Superhero(2L, "Superman"));

        when(superheroRepository.findAll()).thenReturn(superheroes);

        // Act
        List<Superhero> result = superheroService.getAllSuperheroes();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Spiderman", result.get(0).getName());
        assertEquals("Superman", result.get(1).getName());
    }

    @Test
    public void testGetSuperheroesByName() {
        // Arrange
        String name = "Spider";
        List<Superhero> superheroes = new ArrayList<>();
        superheroes.add(new Superhero(1L, "Spiderman"));

        when(superheroRepository.findByNameContainingIgnoreCase(name)).thenReturn(superheroes);

        // Act
        List<Superhero> result = superheroService.getSuperheroesByName(name);

        // Assert
        assertEquals(1, result.size());
        assertEquals("Spiderman", result.get(0).getName());
    }


    @SuppressWarnings("null")
    @Test
    public void testCreateSuperhero() {
        // Arrange
        Superhero superheroToCreate = new Superhero(1L, "Iron Man");
        when(superheroRepository.save(any(Superhero.class))).thenReturn(superheroToCreate);

        // Act
        Superhero result = superheroService.createSuperhero(superheroToCreate);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("Iron Man", result.getName());
    }

}
