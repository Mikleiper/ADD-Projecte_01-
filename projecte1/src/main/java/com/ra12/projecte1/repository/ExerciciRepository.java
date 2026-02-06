package com.ra12.projecte1.repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ra12.projecte1.model.Exercici;

@Repository
public class ExerciciRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class ExerciciRowMap implements RowMapper<Exercici> {
        
        @Override
        public Exercici mapRow(ResultSet rs, int rowNum) throws SQLException{
            Exercici exercici = new Exercici();
            
            exercici.setId(rs.getLong("id"));
            exercici.setNivell(rs.getInt("nivell"));
            exercici.setTipus(rs.getString("tipus"));
            exercici.setDurada(rs.getInt("durada"));
            exercici.setMaterial(rs.getString("materia"));
            exercici.setImagen(rs.getString("imagen"));
            exercici.setUltimAcces(rs.getTimestamp("ultimAcess"));
            exercici.setDataCreated(rs.getTimestamp("dataCreated"));
            exercici.setDataUpdated(rs.getTimestamp("dataUpate"));
            return exercici;
        }
    }

    public int updateImagePath(Long id, String imagePath) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String sql = "UPDATE exercici SET imagen = ?, dataUpdated = ? WHERE id = ?";
        return jdbcTemplate.update(sql, imagePath, now, id);
    }

    // Actualitza totes les dades d’un exercici.
    public int updateExercici(Exercici exercici, Long id) {
        String sql = "UPDATE `exercici` SET nivell = ?, tipus = ?, durada = ?, material = ?, dataUpdated = ? WHERE id = ?";
        return jdbcTemplate.update(sql, exercici.getNivell(), exercici.getTipus(), exercici.getDurada(), exercici.getMaterial(), new Timestamp(System.currentTimeMillis()), id);
    }

    // Elimina un usuari pel seu ID.
    public int deleteById(Long id) {
        String sql = "DELETE FROM exercici WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Elimina tots els usuaris de la base de dades.
    public int deleteAll() {
        String sql = "DELETE FROM exercici";
        return jdbcTemplate.update(sql);
    }

}
