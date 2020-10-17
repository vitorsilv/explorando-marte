package com.example.explorando_marte.controllers;

import com.example.explorando_marte.models.Planet;
import com.example.explorando_marte.models.Probe;
import com.example.explorando_marte.repositories.PlanetRepository;
import com.example.explorando_marte.repositories.ProbeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/movement")
public class MovementController {
    @Autowired
    private ProbeRepository repository;

    @Autowired
    private PlanetRepository planetRepository;

    @PutMapping("/{idProbe}/{command}")
    @Transactional
    public ResponseEntity moveProbe(@PathVariable("idProbe") int idProbe,
                                    @PathVariable("command") String command){
        Optional<Probe> probe = repository.findById(idProbe);
        List<Probe> probesList = repository.findAll();
        if(repository.existsById(idProbe)) {
            switch (command) {
                case "L":
                    probe.get().turnLeft();
                    this.repository.save(probe.get());
                    return ok().body(probe.get());
                case "R":
                    probe.get().turnRight();
                    this.repository.save(probe.get());
                    return ok().body(probe.get());
                case "M":
                    probe.get().move();
                    for (Probe p: probesList) {

                        if(((probe.get().getPositionY() == p.getPositionY())
                                && (probe.get().getPositionX() == p.getPositionX())) && !p.equals(probe.get())){
                            throw new RuntimeException("Você iria bater na sonda de id "+ p.getId());
                        }
                    }
                    this.repository.save(probe.get());
                    return ok().body(probe.get());
                default:
                    return badRequest().body("Movimento inválido, só é permitido L, R e M!");
            }
        }else {
            return notFound().build();
        }
    }
}
