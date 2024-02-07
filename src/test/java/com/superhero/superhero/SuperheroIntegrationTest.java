package com.superhero.superhero;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.superhero.Superhero.SuperheroApplication;
import com.superhero.Superhero.model.Superhero;
import com.superhero.Superhero.repository.SuperheroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = SuperheroApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SuperheroIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SuperheroRepository superheroRepository;

    @BeforeEach
    public void setUp() {
        superheroRepository.deleteAll();
    }

    @Test
    public void testGetAllSuperheroes() throws Exception {
        // Arrange
        Superhero superhero = new Superhero(1L, "Spiderman");
        superheroRepository.save(superhero);

        // Act & Assert
        mockMvc.perform(get("/api/superheroes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Spiderman"));
    }

}
