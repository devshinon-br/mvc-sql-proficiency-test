package mvc.sql.proficiencytest.repository.impl;

import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.model.Vehicle;
import mvc.sql.proficiencytest.model.VehicleModel;
import mvc.sql.proficiencytest.repository.VehicleRepository;
import mvc.sql.proficiencytest.repository.rowmapper.VehicleRowMapper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class VehicleRepositoryImp implements VehicleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final VehicleRowMapper vehicleRowMapper;

    @Autowired
    public VehicleRepositoryImp(final JdbcTemplate jdbcTemplate, final VehicleRowMapper vehicleRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.vehicleRowMapper = vehicleRowMapper;
    }

    @Override
    public Vehicle findVehicleById(final UUID id) {
        if (id != null) {
            final String sql = "SELECT id, license_plate, model, color FROM vehicle WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, vehicleRowMapper);
        }

        return null;
    }

    @Override
    public Vehicle findVehicleByLicensePlate(final String licensePlate){
        try {
            final String sql = "SELECT id, license_plate, model, color FROM vehicle where license_plate = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{licensePlate}, vehicleRowMapper);
        } catch (final Exception e) {
            return null;
        }
    }

    @Override
    public List<Pair<Vehicle, Ticket>> findVehiclesWhereDepartureTimeIsNull() {
        final String sql =
                "SELECT " +
                "v.id, " +
                "v.license_plate, " +
                "v.model, v.color, " +
                "t.id AS ticket_id, " +
                "t.vehicle_id, " +
                "t.entry_time, " +
                "t.departure_time " +
                "FROM vehicle v " +
                "LEFT JOIN ticket t ON t.vehicle_id = v.id " +
                "WHERE t.departure_time IS NULL";

        return jdbcTemplate.query(sql, rs -> {
            final List<Pair<Vehicle, Ticket>> result = new ArrayList<>();
            while (rs.next()) {
                final Vehicle vehicle = new Vehicle();
                vehicle.setId(UUID.fromString(rs.getString("id")));
                vehicle.setLicensePlate(rs.getString("license_plate"));
                vehicle.setModel(VehicleModel.getVehicleModel(rs.getString("model")));
                vehicle.setColor(rs.getString("color"));

                final Ticket ticket = new Ticket();
                ticket.setId(UUID.fromString(rs.getString("ticket_id")));
                ticket.setVehicle(vehicle);
                ticket.setEntryTime(rs.getTimestamp("entry_time").toLocalDateTime());
                ticket.setDepartureTime(rs.getTimestamp("departure_time") != null ? rs.getTimestamp("departure_time").toLocalDateTime() : null);

                result.add(new ImmutablePair<>(vehicle, ticket));
            }
            return result;
        });
    }

    @Override
    public List<Vehicle> listVehicles() {
        final String sql = "SELECT id, license_plate, model, color FROM vehicle";
        return jdbcTemplate.query(sql, vehicleRowMapper);
    }

    @Override
    public void createVehicle(final Vehicle vehicle) {
        if (vehicle != null) {
            final String sql = "INSERT INTO vehicle (id, license_plate, model, color) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, vehicle.getId(), vehicle.getLicensePlate(), vehicle.getModel().getDescription(), vehicle.getColor());
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
