package com.example.explorando_marte.controllers;

import com.example.explorando_marte.models.Planet;
import com.example.explorando_marte.models.Probe;
import com.example.explorando_marte.repositories.PlanetRepository;
import com.example.explorando_marte.repositories.ProbeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;
@RestController
@RequestMapping("/probe")
public class ProbeController {
    @Autowired
    private ProbeRepository repository;

    @Autowired
    private PlanetRepository planetRepository;

    @PostMapping()
    @Transactional
    public ResponseEntity createProbe(@RequestBody Probe newProbe){
        Optional<Planet> planet = this.planetRepository.findById(newProbe.getPlanet().getId());
        //Verificando se a posição da sonda esta fora dos limites do planeta
        if ((newProbe.getPositionX() > planet.get().getX() || newProbe.getPositionX() < 0)
                || (newProbe.getPositionY() > planet.get().getY() || newProbe.getPositionY() < 0)) {
            return badRequest().body("Fora dos limites do planeta");
        }else {
            List<Probe> probes = this.repository.findAll();
            //Verificando se a nova sonda vai pousar em cima de outra
            for (Probe p: probes) {
                if((p.getPositionX() == newProbe.getPositionX())
                        && (p.getPositionY() == newProbe.getPositionY())){
                    return badRequest().body(String.format("Erro em calular a rota!\nA nova sonda está iria bater na sonda de id %d\nEnvie uma rota de pouso livre :D",p.getId()));
                }
            }
            this.repository.save(newProbe);
            return created(null).build();
        }
    }

    @GetMapping()
    public ResponseEntity readAllProbes(){
        List<Probe> probes = this.repository.findAll();
        if(probes.size() > 0){
            return ok(probes);
        }else {
            return noContent().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity readOneProbe(@PathVariable int id){
        Optional<Probe> queryProbe = this.repository.findById(id);

        if (queryProbe.isPresent()){
            return ok(queryProbe.get());
        }else{
            return notFound().build();
        }
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity updateProbe(@RequestBody Probe probe,
                                       @PathVariable int id) {
        if (this.repository.existsById(id)) {
            probe.setId(id);
            this.repository.save(probe);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProbe(@PathVariable int id){
        if(this.repository.existsById(id)){
            this.repository.deleteById(id);
            return ok().build();
        } else {
            return notFound().build();
        }
    }
}
