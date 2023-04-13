package mvc.sql.proficiencytest.repository.rowmapper;

import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.model.Vehicle;
import mvc.sql.proficiencytest.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

@Component
public class TicketRowMapper implements RowMapper<Ticket> {

    private final VehicleService vehicleService;

    @Autowired
    public TicketRowMapper(final VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Override
    public Ticket mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Ticket ticket = new Ticket();

        final String id = rs.getString("id");
        if (id != null) {
            ticket.setId(UUID.fromString(id));
        }

        final String vehicleId = rs.getString("vehicle_id");
        if (vehicleId != null) {
            final Vehicle vehicle = vehicleService.findVehicleById(UUID.fromString(vehicleId));
            ticket.setVehicle(vehicle);
        }

        final String entryTime = rs.getString("entry_time");
        if (entryTime != null) {
            ticket.setEntryTime(Timestamp.valueOf(entryTime).toLocalDateTime());
        }

        final String departureTime = rs.getString("departure_time");
        if (departureTime != null) {
            ticket.setDepartureTime(Timestamp.valueOf(entryTime).toLocalDateTime());
        }

        return ticket;
    }
}
