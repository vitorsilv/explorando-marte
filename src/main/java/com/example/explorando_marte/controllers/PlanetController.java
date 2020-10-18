package com.example.explorando_marte.controllers;

import com.example.explorando_marte.repositories.PlanetRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import  com.example.explorando_marte.models.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@ApiOperation(value = "Rotas para criar, listar, alterar e deletar planetas")
@RestController
@RequestMapping("/map")
public class PlanetController {

    @Autowired
    private PlanetRepository repository;

    @ApiOperation(value = "Cria um planeta")
    @PostMapping(produces="application/json", consumes="application/json")
    @Transactional
    public ResponseEntity createMap(@RequestBody Planet newPlanet){
        this.repository.save(newPlanet);
        return created(null).build();
    }

    @ApiOperation(value = "Lista todos os planetas criados")
    @GetMapping(produces="application/json")
    public ResponseEntity readAllPlanets(){
        List<Planet> planets = this.repository.findAll();
        if(planets.size() > 0){
            return ok(planets);
        }else {
            return noContent().build();
        }
    }

    @ApiOperation(value = "Consulta um planeta baseado no seu id")
    @GetMapping(value = "{id}", produces="application/json")
    public ResponseEntity readOnePlanet(@PathVariable int id){
        Optional<Planet> queryMap = this.repository.findById(id);

        if (queryMap.isPresent()){
            return ok(queryMap.get());
        }else{
            return notFound().build();
        }
    }

    @ApiOperation(value = "Altera um planeta baseado no seu id")
    @PutMapping(value = "{id}", produces="application/json", consumes="application/json")
    @Transactional
    public ResponseEntity updatePlanet(@RequestBody Planet planet,
                                         @PathVariable int id) {
        if (this.repository.existsById(id)) {
            planet.setId(id);
            this.repository.save(planet);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @ApiOperation(value = "Deleta um planeta baseado no seu id")
    @DeleteMapping(value = "{id}", produces="application/json")
    @Transactional
    public ResponseEntity deletePlanet(@PathVariable int id){
        if(this.repository.existsById(id)){
            this.repository.deleteById(id);
            return ok().build();
        } else {
            return notFound().build();
        }
    }


}
