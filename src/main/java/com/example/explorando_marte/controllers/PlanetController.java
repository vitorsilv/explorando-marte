package com.example.explorando_marte.controllers;

import com.example.explorando_marte.repositories.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import  com.example.explorando_marte.models.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/map")
public class PlanetController {

    @Autowired
    private PlanetRepository repository;

    @PostMapping()
    @Transactional
    public ResponseEntity createMap(@RequestBody Planet newPlanet){
        this.repository.save(newPlanet);
        return created(null).build();
    }

    @GetMapping()
    public ResponseEntity readAllPlanets(){
        List<Planet> planets = this.repository.findAll();
        if(planets.size() > 0){
            return ok(planets);
        }else {
            return noContent().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity readOnePlanet(@PathVariable Integer id){
        Optional<Planet> queryMap = this.repository.findById(id);

        if (queryMap.isPresent()){
            return ok(queryMap.get());
        }else{
            return notFound().build();
        }
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity updatePlanet(@RequestBody Planet planet,
                                         @PathVariable Integer id) {
        if (this.repository.existsById(id)) {
            planet.setId(id);
            this.repository.save(planet);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePlanet(@PathVariable Integer id){
        if(this.repository.existsById(id)){
            this.repository.deleteById(id);
            return ok().build();
        } else {
            return notFound().build();
        }
    }


}
