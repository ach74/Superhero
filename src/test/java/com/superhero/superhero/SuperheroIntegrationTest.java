package com.superhero.superhero;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.superhero.Superhero.SuperheroApplication;
import com.superhero.Superhero.model.Superhero;
import com.superhero.Superhero.repository.SuperheroRepository;
import com.superhero.Superhero.service.SuperheroService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SuperheroApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SuperheroIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SuperheroRepository superheroRepository;

    @Autowired
    private SuperheroService superheroService;

    @BeforeEach
    public void setUp() {
        superheroRepository.deleteAll();
    }

    @SuppressWarnings("null")
    @Test
    public void testGetAllSuperheroes() throws Exception {
        Superhero superhero = new Superhero(1L, "Spiderman");
        superheroRepository.save(superhero);

        mockMvc.perform(get("/api/superheroes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Spiderman"));
    }

    @Test
    public void testGetSuperheroByIdEndpoint() throws Exception {
        Superhero savedSuperhero = superheroService.createSuperhero(new Superhero(null, "Spiderman"));
    
        mockMvc.perform(get("/api/superheroes/{id}", savedSuperhero.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Spiderman"))
                .andExpect(jsonPath("$.id").value(savedSuperhero.getId()));
    
    }


    @Test
    public void testCreateSuperheroes() throws Exception {
        Superhero superheroToCreate = new Superhero();
        superheroToCreate.setName("Spiderman");

        mockMvc.perform(post("/api/superheroes/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(superheroToCreate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Spiderman"))
                .andExpect(jsonPath("$.id").exists());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
