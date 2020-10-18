package com.example.explorando_marte.controllers;

import com.example.explorando_marte.models.Planet;
import com.example.explorando_marte.models.Probe;
import com.example.explorando_marte.repositories.PlanetRepository;
import com.example.explorando_marte.repositories.ProbeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PlanetControllerTest {
    @Autowired
    PlanetController controller;

    @MockBean
    PlanetRepository repository;

    @Test
    @DisplayName("Testando configurações das rotas de PlanetController")
    void testConfig() throws NoSuchMethodException {
        Class controllerClass = PlanetController.class;

        assertTrue(controllerClass.isAnnotationPresent(RestController.class),
                "A classe está anotada com @RestController");

        //Teste readOnePlanet
        Method readOnePlanet = controllerClass.getDeclaredMethod("readOnePlanet", int.class);
        assertTrue(readOnePlanet.isAnnotationPresent(GetMapping.class),
                "Método readOnePlanet() deve estar anotado com @GetMapping");

        String expectedUri = "{id}";
        assertEquals(expectedUri, readOnePlanet.getDeclaredAnnotation(GetMapping.class).value()[0],
                "A URI de readOnePlanet() deve ser "+expectedUri);

        //Teste deletePlanet
        Method deletePlanet = controllerClass.getDeclaredMethod("deletePlanet", int.class);
        assertTrue(deletePlanet.isAnnotationPresent(DeleteMapping.class),
                "Método deletePlanet() deve estar anotado com @DeleteMapping");

        expectedUri = "/{id}";
        assertEquals(expectedUri, deletePlanet.getDeclaredAnnotation(DeleteMapping.class).value()[0],
                "A URI de deletePlanet() deve ser "+expectedUri);

        //Teste updatePlanet
        Method updatePlanet = controllerClass.getDeclaredMethod("updatePlanet", Planet.class, int.class);
        assertTrue(updatePlanet.isAnnotationPresent(PutMapping.class),
                "Método updatePlanet() deve estar anotado com @PutMapping");

        expectedUri = "/{id}";
        assertEquals(expectedUri, updatePlanet.getDeclaredAnnotation(PutMapping.class).value()[0],
                "A URI de updatePlanet() deve ser "+expectedUri);

        //Teste createMap
        Method createMap = controllerClass.getDeclaredMethod("createMap", Planet.class);
        assertTrue(createMap.isAnnotationPresent(PostMapping.class),
                "Método createMap() deve estar anotado com @PostMapping");
    }

    @Test
    @DisplayName("Listando todos os planetas")
    void readAllPlanets() {
        //Cenário 1 - Retornar uma lista com varios planetas = 200
        List<Planet> probeList = Arrays.asList(new Planet(), new Planet(), new Planet());
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>(probeList));

        ResponseEntity response = controller.readAllPlanets();

        assertEquals(probeList, response.getBody(),
                "A lista do repository deve ser a mesma da resposta do controller");

        assertEquals(200, response.getStatusCodeValue());

        // Cenário 2 - Retornar uma lista vazia = 204
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());

        response = controller.readAllPlanets();

        assertNull(response.getBody(), "O corpo da resposta deve ser nulo");

        assertEquals(204, response.getStatusCodeValue(), "Consulta sem conteúdo deve retornar 204");

    }

    @Test
    @DisplayName("Consultando um planeta")
    void readOnePlanet() {
        //Criando um paneta falso para ser usado pelo Mock de sonda
        Integer id = 1;

        Planet planet = new Planet();
        planet.setId(id);
        planet.setPlanetName("Terra2");
        planet.setX(5);
        planet.setY(5);

        //Cenário 1 - Planeta encontrado com sucesso
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(planet));

        ResponseEntity response = controller.readOnePlanet(id);

        assertEquals(200, response.getStatusCodeValue(),
                "Planeta encontrado deve retornar status 200");

        assertEquals(planet, response.getBody(),
                "O planeta que o repository retornou deve ser o mesmo do controller");

        //Cenário 2 - Planeta não encontrado
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        response = controller.readOnePlanet(555);

        assertEquals(404, response.getStatusCodeValue(),
                "Consulta por id inválido deve retornar status 404");

        assertNull(response.getBody(), "Consulta por id inválido deve vir sem corpo");
    }
}