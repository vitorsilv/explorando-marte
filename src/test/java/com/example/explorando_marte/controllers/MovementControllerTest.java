package com.example.explorando_marte.controllers;

import com.example.explorando_marte.models.Planet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MovementControllerTest {
    @Test
    @DisplayName("Testando configurações das rotas de MovementController")
    void testConfig() throws NoSuchMethodException {
        Class controllerClass = MovementController.class;

        assertTrue(controllerClass.isAnnotationPresent(RestController.class),
                "A classe está anotada com @RestController");


        //Teste moveProbe
        Method moveProbe = controllerClass.getDeclaredMethod("moveProbe", int.class, String.class);
        assertTrue(moveProbe.isAnnotationPresent(PutMapping.class),
                "Método moveProbe() deve estar anotado com @PutMapping");

        String expectedUri = "/{idProbe}/{command}";
        assertEquals(expectedUri, moveProbe.getDeclaredAnnotation(PutMapping.class).value()[0],
                "A URI de moveProbe() deve ser "+expectedUri);
    }
}