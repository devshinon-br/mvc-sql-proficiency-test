package mvc.sql.proficiencytest.repository.mapper;

import mvc.sql.proficiencytest.model.Attendant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendantMapper implements RowMapper<Attendant> {
    @Override
    public Attendant mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return null;
    }
}