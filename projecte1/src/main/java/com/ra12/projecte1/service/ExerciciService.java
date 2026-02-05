package com.ra12.projecte1.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ra12.projecte1.repository.ExerciciRepository;

import tools.jackson.databind.ObjectMapper;

@Service
public class ExerciciService {

    @Autowired
    private ExerciciRepository exercicisRepository;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private CustomLogging customLogging;

    private static final String CLASS_NAME = "ExercicisService";

    public String[] saveExerciciImage(Long id, MultipartFile imageFile) throws IOException{
        customLogging.info(CLASS_NAME, "uploadImage", "Afegint la imatge" + imageFile.getOriginalFilename() + "de l'exercici amb id: " + id);
        Exercici exercici = exercicisRepository.findOne(id);
        
        if(exercici != null){
            Path novaCarpeta = Paths.get("uploads/images");  //CREEM NOVA CARPETA //.get és = a new File en NIO2
            if (Files.notExists(novaCarpeta)) {   //si no existeix la carpeta
                Files.createDirectories(novaCarpeta);  //executem i creem carpeta
            }
            String fileName = imageFile.getOriginalFilename();  //Retorna el nom original del fitxer tal com el tenia l’usuari en el seu sistema
            Path destination = novaCarpeta.resolve(fileName);  //Guaradarem la imatge amb aquest nom

            try (InputStream in = imageFile.getInputStream()) {
                Files.copy(in, destination, StandardCopyOption.REPLACE_EXISTING);
            }
            
            String relativePath = "/images/" + fileName;  // NO guardem la ruta absoluta del sistema d'arxius-això és quan guardem físicament l'arxiu en un discdur, SÍ guardem la ruta relativa per la BBDD
            exercicisRepository.updateImagePath(id, relativePath);
            customLogging.info(CLASS_NAME, "uploadImage", "La imatge s'ha guardat correctament. El path és : " + relativePath);    
            return new String[] {"ok", "Imatge pujada correctament."};
        } else {
            customLogging.error(CLASS_NAME, "uploadImage", "l'Exercici amb id " + id +" no existeix", null);
            return new String[] {"error", "Error al guardar la imatge:"};
        }
    }
}
