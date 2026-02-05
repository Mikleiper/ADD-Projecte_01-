package com.ra12.projecte1.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ExercicisController {

    @Autowired
    private ExercicisService userService;

    @Autowired
    private CustomLogging customLogging;

    //Endpoint per pujar imatge
    @PostMapping("/exercicis/{exercici_id}/image")
    public ResponseEntity<String> uploadExerciciImage(@PathVariable Long exercici_id, @RequestParam MultipartFile imageFile) throws IOException {
        return ResponseEntity.ok(ExercicisService.saveExerciciImage(exercici_id, imageFile));
    }
}
