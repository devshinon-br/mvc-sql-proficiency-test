package mvc.sql.proficiencytest.repository.impl;

import mvc.sql.proficiencytest.model.Vehicle;
import mvc.sql.proficiencytest.repository.VehicleRepository;
import mvc.sql.proficiencytest.repository.mapper.VehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class VehicleRepositoryImp implements VehicleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Vehicle findVehicleById(final UUID id) {
        if (id != null) {
            final String sql = "SELECT id, license_plate, model, color FROM vehicle WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new VehicleMapper());
        }

        return null;
    }

    @Override
    public void createVehicle(final Vehicle vehicle) {

    }

    @Override
    public void updateVehicle(final Vehicle vehicle) {

    }

    @Override
    public void deleteVehicle(final UUID id) {

    }

}
