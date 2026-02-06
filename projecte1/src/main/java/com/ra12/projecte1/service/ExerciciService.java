package com.ra12.projecte1.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ra12.projecte1.logging.CustomLogging;
import com.ra12.projecte1.dto.ExerciciRequestDTO;
import com.ra12.projecte1.model.Exercici;
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

    public int saveExerciciImage(Long id, MultipartFile imageFile) throws IOException{
        customLogging.info(CLASS_NAME, "uploadImage", "Afegint la imatge" + imageFile.getOriginalFilename() + "de l'exercici amb id: " + id);
        Exercici exercici = exercicisRepository.findOne(id);    //------------CANVIAR POR METODO D OMAR D GET X ID    
        if(exercici != null){
            Path novaCarpeta = Paths.get("uploads/images");  //CREEM NOVA CARPETA //.get és = a new File en NIO2
            if (Files.notExists(novaCarpeta)) {   //si no existeix la carpeta
                Files.createDirectories(novaCarpeta);  //executem i creem carpeta
            }

            String uniqueFileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();  //Retorna el nom original el temps actual per nosobreesciurefotos q tinguin el mateix nom
            
            Path destination = novaCarpeta.resolve(uniqueFileName);  //Guaradarem la imatge amb aquest nom

            try (InputStream in = imageFile.getInputStream()) {
                Files.copy(in, destination, StandardCopyOption.REPLACE_EXISTING);
            }
            
            String relativePath = "/images/" + uniqueFileName;  // NO guardem la ruta absoluta del sistema d'arxius-això és quan guardem físicament l'arxiu en un discdur, SÍ guardem la ruta relativa per la BBDD
            try {
                exercicisRepository.updateImagePath(id, relativePath);
                customLogging.info(CLASS_NAME, "uploadImage", "La imatge s'ha guardat correctament. El path és : " + relativePath);
                return 1;
            } catch (Exception e) {
                customLogging.error(CLASS_NAME, "uploadImage", "Error al guardar la imatge a la base de dades: " + e.getMessage(), e);
                return 2;
            }               
        } else {
            customLogging.error(CLASS_NAME, "uploadImage", "l'Exercici amb id " + id +" no existeix", null);
            return 0;
        }
    }

    public int updateExercici(Long id, ExerciciRequestDTO exerciciRequestDTO) {
        Exercici exercici = exerciciRequestDTO.toExercici();
        customLogging.info(CLASS_NAME, "updateExercici", "Modificant l'exercici amb id: " + id);
        int result = exercicisRepository.updateExercici(exercici, id);
        if (result == 0) {
            customLogging.error(CLASS_NAME, "updateExercici", "L'exercici amb id: " + id + " no existeix.", null);
        } else {
            customLogging.info(CLASS_NAME, "updateExercici", "Exercici modificat correctament.");
        }
        return result;
    }

    public int deleteExercici(Long id) {
        customLogging.info(CLASS_NAME, "deleteExercici", "Borrant l'exercici amb id: " + id);
        int resultat = exercicisRepository.deleteById(id);
        if (resultat == 0) {
            customLogging.error(CLASS_NAME, "deleteExercici", "L'exercici amb id: " + id + " no existeix.", null);
        } else {
            customLogging.info(CLASS_NAME, "deleteExercici", "L'exercici amb id: " + id + " s'ha borrat correctament.");
        }
        return resultat;
    }

    public int deleteAllExercicis() {
        customLogging.info(CLASS_NAME, "deleteAllExercicis", "Borrant tots els exercicis de la base de dades.");
        int resultat = exercicisRepository.deleteAll();
        if (resultat == 0) {
            customLogging.error(CLASS_NAME, "deleteAllExercicis", "No hi ha exercicis a la base de dades per borrar.", null);
        } else {
            customLogging.info(CLASS_NAME, "deleteAllExercicis", "Tots els exercicis s'han borrat correctament. Total d'exercicis borrats: " + resultat);
        }
        return resultat;
    }
}
