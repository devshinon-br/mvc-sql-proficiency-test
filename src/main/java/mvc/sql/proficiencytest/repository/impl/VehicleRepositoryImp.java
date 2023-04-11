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
        if (vehicle != null) {
            final String sql = "INSERT INTO vehicle (id, license_plate, model, color) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, vehicle.getId(), vehicle.getLicensePlate(), vehicle.getModel(), vehicle.getColor());
        }
    }

    @Override
    public void updateVehicle(final Vehicle vehicle) {
        if (vehicle != null) {
            final String sql = "UPDATE vehicle license_plate = ?, model = ?, color = ? WHERE id = ?";
            jdbcTemplate.update(sql, vehicle.getLicensePlate(), vehicle.getModel(), vehicle.getColor(), vehicle.getId());
        }
    }

    @Override
    public void deleteVehicle(final UUID id) {
        if (id != null) {
            final String sql = "DELETE FROM vehicle WHERE id = ?";
            jdbcTemplate.update(sql, id);
        }
    }

}
