package com.ra12.projecte1.repository;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ra12.projecte1.model.Exercici;

@Repository
public class ExercicisRepository {

    private JdbcTemplate jdbcTemplate;

    private static final class ExercicisRowMap implements RowMapper<Exercici> {
        
        @Override
        public Exercici mapRow(ResultSet rs, int rowNum) throws SQLException{
            Exercici exercici = new Exercici();
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
}
