package com.example.explorando_marte.controllers;

import com.example.explorando_marte.models.Planet;
import com.example.explorando_marte.models.Probe;
import com.example.explorando_marte.repositories.ProbeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProbeControllerTest {
    @Autowired
    ProbeController controller;

    @MockBean
    ProbeRepository repository;

    @Test
    @DisplayName("Testando configurações das rotas de ProbeController")
    void testConfig() throws NoSuchMethodException {
        Class controllerClass = ProbeController.class;

        assertTrue(controllerClass.isAnnotationPresent(RestController.class),
                "A classe está anotada com @RestController");

        //Teste readOneProbe
        Method readOneProbe = controllerClass.getDeclaredMethod("readOneProbe", int.class);
        assertTrue(readOneProbe.isAnnotationPresent(GetMapping.class),
                "Método readOneProbe() deve estar anotado com @GetMapping");

        String expectedUri = "/{id}";
        assertEquals(expectedUri, readOneProbe.getDeclaredAnnotation(GetMapping.class).value()[0],
                "A URI de readOneProbe() deve ser "+expectedUri);

        //Teste deleteProbe
        Method deleteProbe = controllerClass.getDeclaredMethod("deleteProbe", int.class);
        assertTrue(deleteProbe.isAnnotationPresent(DeleteMapping.class),
                "Método deleteProbe() deve estar anotado com @DeleteMapping");

        expectedUri = "/{id}";
        assertEquals(expectedUri, deleteProbe.getDeclaredAnnotation(DeleteMapping.class).value()[0],
                "A URI de deleteProbe() deve ser "+expectedUri);

        //Teste createProbe
        Method createProbe = controllerClass.getDeclaredMethod("createProbe", Probe.class);
        assertTrue(createProbe.isAnnotationPresent(PostMapping.class),
                "Método createProbe() deve estar anotado com @PostMapping");
    }

    @Test
    @DisplayName("Listando todas as sondas")
    void readAllProbes(){
        //Cenário 1 - Retornar uma lista com varias sondas = 200
        List<Probe> probeList = Arrays.asList(new Probe(), new Probe(), new Probe());
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>(probeList));

        ResponseEntity response = controller.readAllProbes();

        assertEquals(probeList, response.getBody(),
                "A lista do repository deve ser a mesma da resposta do controller");

        assertEquals(200, response.getStatusCodeValue());

        // Cenário 2 - Retornar uma lista vazia = 204
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());

        response = controller.readAllProbes();

        assertNull(response.getBody(), "O corpo da resposta deve ser nulo");

        assertEquals(204, response.getStatusCodeValue(), "Consulta sem conteúdo deve retornar 204");

    }

    @Test
    @DisplayName("Consultando uma sonda")
    void readOneProbe(){
        //Criando um paneta falso para ser usado pelo Mock de sonda

        Planet planet = new Planet();
        planet.setId(1);
        planet.setPlanetName("Terra2");
        planet.setX(5);
        planet.setY(5);

        //Criando uma sonda falsa para ser usada pelo Mock

        Integer id = 1;

        Probe probe = new Probe();
        probe.setId(id);
        probe.setPositionX(3);
        probe.setPositionY(3);
        probe.setProbeDirection("N");
        probe.setPlanet(planet);

        //Cenário 1 - Sonda encontrada com sucesso
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(probe));

        ResponseEntity response = controller.readOneProbe(id);

        assertEquals(200, response.getStatusCodeValue(),
                "Sonda encontrada deve retornar status 200");

        assertEquals(probe, response.getBody(),
                "A sonda que o repository retornou deve ser o mesmo do controller");

        //Cenário 2 - Sonda não encontrada
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        response = controller.readOneProbe(798);

        assertEquals(404, response.getStatusCodeValue(),
                "Consulta por id inválido deve retornar status 404");

        assertNull(response.getBody(), "Consulta por id inválido deve vir sem corpo");
    }
}