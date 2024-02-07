package com.superhero.Superhero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.superhero.Superhero.model.Superhero;

public interface SuperheroRepository extends JpaRepository<Superhero, Long>{

    List<Superhero> findByNameContainingIgnoreCase(String name);

}