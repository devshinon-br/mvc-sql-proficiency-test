package mvc.sql.proficiencytest.repository.mapper;

import mvc.sql.proficiencytest.model.Vehicle;
import mvc.sql.proficiencytest.model.VehicleModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class VehicleMapper implements RowMapper<Vehicle> {
    @Override
    public Vehicle mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Vehicle vehicle = new Vehicle();
        vehicle.setId(UUID.fromString(rs.getString("id")));
        vehicle.setLicensePlate(rs.getString("license_plate"));
        vehicle.setModel(VehicleModel.valueOf(rs.getString("model")));
        vehicle.setColor(rs.getString("color"));
        return vehicle;
    }
}
