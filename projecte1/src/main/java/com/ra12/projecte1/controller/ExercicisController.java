package com.ra12.projecte1.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ra12.projecte1.model.Exercici;
import com.ra12.projecte1.service.ExercicisService;

@RestController
@RequestMapping("/api")
public class ExercicisController {

    @Autowired
    private ExercicisService userService;

    @Autowired
    private CustomLogging customLogging;

    //Endpoint per pujar imatge
    @PostMapping("/exercicis/{id}/image")
    public ResponseEntity<String> uploadExerciciImage(@PathVariable Long id, @RequestParam MultipartFile imageFile) throws IOException {
        return ResponseEntity.ok(ExercicisService.saveExerciciImage(id, imageFile));
    }

    // Endpoint per actualitzar un exercici pel seu ID.
    @PutMapping("/exercicis/{id}")
    public ResponseEntity<String> updateExercici(@PathVariable Long id, @RequestBody Exercici exercici) { 
        int actulitzat = userService.updateExercici(id, exercici);
        return (actulitzat == 0) ?  ResponseEntity.status(HttpStatus.OK).body("No s'ha efectuat cap canvi") : ResponseEntity.status(HttpStatus.OK).body(String.format("L'exercici amb id %d s'ha actualitzat satisfactoriament", id));
    }
    
    // Endpoint per eliminar un exercici pel seu ID.
    @DeleteMapping("/exercicis/{id}")
    public ResponseEntity<String> deleteExercici(@PathVariable Long id) {
        int esborrat = userService.deleteExercici(id);
        return (esborrat > 0)? ResponseEntity.status(HttpStatus.OK).body("Exercici amb id=" + id + " eliminat correctament de la base de dades.") : ResponseEntity.status(HttpStatus.OK).body("No s'ha esborrat res, l'exercici cercat no existeix");
    }

    // Endpoint per eliminar tots els exercicis de la base de dades.
    @DeleteMapping("/exercicis")
    public ResponseEntity<String> deleteAllExercicis() {
        int allEsborrat =userService.deleteAllExercicis();
        return (allEsborrat > 0)? ResponseEntity.status(HttpStatus.OK).body("Exercicis eliminats correctament.") : ResponseEntity.status(HttpStatus.OK).body("No s'ha esborrat res, no hi ha exercicis a la base de dades");
    }

}
