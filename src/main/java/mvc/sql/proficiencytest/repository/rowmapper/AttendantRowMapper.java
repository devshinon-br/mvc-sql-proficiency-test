package mvc.sql.proficiencytest.repository.rowmapper;

import mvc.sql.proficiencytest.model.Attendant;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class AttendantRowMapper implements RowMapper<Attendant> {
    @Override
    public Attendant mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Attendant attendant = new Attendant();

        attendant.setId(UUID.fromString(rs.getString("id")));
        attendant.setName(rs.getString("name"));

        return attendant;
    }
}
