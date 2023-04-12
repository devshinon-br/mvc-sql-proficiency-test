package mvc.sql.proficiencytest.repository.impl;

import mvc.sql.proficiencytest.model.Attendant;
import mvc.sql.proficiencytest.repository.AttendantRepository;
import mvc.sql.proficiencytest.repository.mapper.AttendantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AttendantRepositoryImp implements AttendantRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Attendant findAttendantById(final UUID id) {
        if (id != null) {
            final String sql = "SELECT id, name FROM attendant WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new AttendantMapper());
        }

        return null;
    }

    @Override
    public List<Attendant> listAttendants() {
        final String sql = "SELECT id, name FROM attendant";
        return jdbcTemplate.query(sql, new AttendantMapper());
    }

    @Override
    public void createAttendant(final Attendant attendant) {
        if (attendant != null) {
            final String sql = "INSERT INTO attendant (id, name) VALUES (?, ?)";
            jdbcTemplate.update(sql, attendant.getId(), attendant.getName());
        }
    }

    @Override
    public void updateAttendant(final Attendant attendant) {
        if (attendant != null) {
            final String sql = "UPDATE attendant name = ? WHERE id = ?";
            jdbcTemplate.update(sql, attendant.getName(), attendant.getId());
        }
    }

    @Override
    public void deleteAttendant(final UUID id) {
        if (id != null) {
            final String sql = "DELETE FROM attendant WHERE id = ?";
            jdbcTemplate.update(sql, id);
        }
    }
}
