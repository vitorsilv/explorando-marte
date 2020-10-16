package com.example.explorando_marte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import  com.example.explorando_marte.models.*;

public interface PlanetRepository extends JpaRepository<Planet, Integer> {
}
