package com.example.explorando_marte.controllers;

import com.example.explorando_marte.models.Planet;
import com.example.explorando_marte.models.Probe;
import com.example.explorando_marte.repositories.PlanetRepository;
import com.example.explorando_marte.repositories.ProbeRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;
@ApiOperation(value = "Rotas para criar, listar e deletar sondas")
@RestController
@RequestMapping("/probe")
public class ProbeController {
    @Autowired
    private ProbeRepository repository;

    @Autowired
    private PlanetRepository planetRepository;



    @ApiOperation(value = "Cria uma nova sonda")
    @PostMapping(produces="application/json", consumes="application/json")
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

    @ApiOperation(value = "Lista todas as sondas cadastradas")
    @GetMapping(produces="application/json")
    public ResponseEntity readAllProbes(){
        List<Probe> probes = this.repository.findAll();
        if(probes.size() > 0){
            return ok(probes);
        }else {
            return noContent().build();
        }
    }

    @ApiOperation(value = "Consulta uma sonda beseada no id")
    @GetMapping(value = "/{id}", produces="application/json")
    public ResponseEntity readOneProbe(@PathVariable int id){
        Optional<Probe> queryProbe = this.repository.findById(id);

        if (queryProbe.isPresent()){
            return ok(queryProbe.get());
        }else{
            return notFound().build();
        }
    }

    @ApiOperation(value = "Deleta uma sonda baseada no id")
    @DeleteMapping(value = "/{id}", produces="application/json")
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
