package com.ra12.projecte1.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ra12.projecte1.dto.ExerciciRequestDTO;
import com.ra12.projecte1.dto.ExerciciResponseDTO;
import com.ra12.projecte1.logging.CustomLogging;
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
        Exercici exercici = exercicisRepository.findById(id);
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

    /* 1.rebem el requestDTO el converteix a entitat Exercici
       2.rep el id per 'separat' */
    public int updateExercici(Long id, ExerciciRequestDTO exerciciRequestDTO) {
        try {
            //mapper copia els valors dels camps q tinguin el mateix nom del DTO a l'entitat Exercici
            Exercici exercici = mapper.convertValue(exerciciRequestDTO, Exercici.class);
            customLogging.info(CLASS_NAME, "updateExercici", "Modificant l'exercici amb id: " + id);
            int resultat = exercicisRepository.updateExercici(exercici, id);
            if (resultat == 0) {
                customLogging.error(CLASS_NAME, "updateExercici", "L'exercici amb id: " + id + " no existeix.", null);
            } else {
                customLogging.info(CLASS_NAME, "updateExercici", "Exercici modificat correctament.");
            }
            return resultat;
        } catch (Exception e) {
            customLogging.error(CLASS_NAME, "updateExercici", "Error al modificar l'exercici: " + e.getMessage(), e);
            return 0;
        }
    }

    public int deleteExercici(Long id) {
        try {
            customLogging.info(CLASS_NAME, "deleteExercici", "Borrant l'exercici amb id: " + id);
            int resultat = exercicisRepository.deleteById(id);
            if (resultat == 0) {
                customLogging.error(CLASS_NAME, "deleteExercici", "L'exercici amb id: " + id + " no existeix.", null);
            } else {
                customLogging.info(CLASS_NAME, "deleteExercici", "L'exercici amb id: " + id + " s'ha borrat correctament.");
            }
            return resultat;
        } catch (Exception e) {
            customLogging.error(CLASS_NAME, "deleteExercici", "Error al borrar l'exercici: " + e.getMessage(), e);
            return 0;
        }
    }

    public int deleteAllExercicis() {
        try {
            customLogging.info(CLASS_NAME, "deleteAllExercicis", "Borrant tots els exercicis de la base de dades.");
            int resultat = exercicisRepository.deleteAll();
            if (resultat == 0) {
                customLogging.error(CLASS_NAME, "deleteAllExercicis", "No hi ha exercicis a la base de dades per borrar.", null);
            } else {
                customLogging.info(CLASS_NAME, "deleteAllExercicis", "Tots els exercicis s'han borrat correctament. Total d'exercicis borrats: " + resultat);
            }
            return resultat;
        } catch (Exception e) {
            customLogging.error(CLASS_NAME, "deleteAllExercicis", "Error al borrar tots els exercicis: " + e.getMessage(), e);
            return 0;
        }
    }

    public int addExercici(ExerciciRequestDTO exerciciRequestDTO){
        customLogging.info(CLASS_NAME, "addExercici", "Intentant crear un nou exercici: ");
        try{
            Exercici exercici = mapper.convertValue(exerciciRequestDTO, Exercici.class);
            int resultat = exercicisRepository.crearExercici(exercici);
         if (resultat == 0) {
                customLogging.error(CLASS_NAME, " addExercici", "L'exercici no s'ha pogut crear a la base de dades.", null);
            } else {
                customLogging.info(CLASS_NAME, " addExercici", "Exercici creat correctament. ID/Files afectades: " + resultat);
            }
            return resultat;
        } catch (Exception e) {
            customLogging.error(CLASS_NAME, " addExercici", "Error en crear l'exercici: " + e.getMessage(), e);
            return 0;
        }
    }
    public int saveExerciciCSV(MultipartFile file) {
        int comptador = 0;
        customLogging.info("ExerciciServices", "saveExerciciCSV"," afegir informació a la taula a través de csv:" + file.getName());

        try(BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            String linia;
            int numLinies = 0;
            while((linia = br.readLine()) != null){
                numLinies ++;
                if(numLinies == 1) continue;
                if(linia.trim().isEmpty()) continue;
                String[] camps = linia.split(",");
                if(camps.length < 4) continue;
                int nivell = Integer.parseInt(camps[0].trim());
                String tipus = camps[1].trim();
                int durada = Integer.parseInt(camps[2].trim());
                String material = camps[3].trim();
                
                // Creem un nou DTO amb les dades del CSV
                ExerciciRequestDTO csvDto = new ExerciciRequestDTO(nivell, tipus, durada, material);
                Exercici exercici = mapper.convertValue(csvDto, Exercici.class);
                try{
                    exercicisRepository.crearExercici(exercici);
                    comptador ++;
                }catch(Exception e){
                    customLogging.error("ExerciciServices", "saveExerciciCSV","Error on line " +linia,e);
                }
            }
            Path csvFolder = Paths.get("src/main/resources/private/csv_processed");
            if (!Files.exists(csvFolder)) {
                try {
                    Files.createDirectories(csvFolder);
                } catch (Exception e) {
                    customLogging.error("ExerciciServices", "saveExercici", "no s'ha pogut crear el csv", e);
                }
            } // Tanquem el IF aquí, abans de guardar el fitxer

            String originalFileName = file.getOriginalFilename();
            Path filePath = csvFolder.resolve(originalFileName);
            try{
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            }catch (Exception e){
                customLogging.error("ExerciciServices", "saveExercici", "no s'ha pogut guardar el arxiu csv", e);
            }
            
        } catch(IOException e){ // Tanquem el TRY aquí
                customLogging.error("ExerciciServices", "saveExercici", "no s'ha pogut llegir el arxiu csv", e);
        }
        customLogging.info("ExerciciServices", "saveExercici", "" );
        return comptador;
        }
        public ExerciciResponseDTO readPerId(Long id){
            customLogging.info("ExerciciServices", "readPerId", "llegir registre per id");
            try{
                Exercici exercici = exercicisRepository.findById(id);
                if(exercici == null){
                customLogging.error("ExerciciServices", "readPerId", "usuari amb id " + id + " no existeix", null);
                return null;
                }
                ExerciciResponseDTO responseDTO = mapper.convertValue(exercici, ExerciciResponseDTO.class);
                return responseDTO;
               
            }catch(Exception e){
                customLogging.error("ExerciciServices", "readPerId", "no s'ha pogut llegir el  registre per id ", e);
                throw e;
            }
        }
        public List<Exercici> readAll(){
            customLogging.info("ExerciciServices", "readPerId", "llegir tots els registres");
            try{
                List<Exercici> exercici = exercicisRepository.findAll();
                return exercici;
            } catch (Exception e) {
                customLogging.error("ExerciciServices", "readAll", "no s'ha pogut llegir els registres", e);
                throw e;    
            }
        }
    }
