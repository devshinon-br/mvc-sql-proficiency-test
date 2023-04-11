package mvc.sql.proficiencytest.repository.mapper;

import mvc.sql.proficiencytest.model.BillingReport;
import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.model.Vehicle;
import mvc.sql.proficiencytest.repository.impl.BillingReportRepositoryImp;
import mvc.sql.proficiencytest.repository.impl.VehicleRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class TicketMapper implements RowMapper<Ticket> {

    @Autowired
    private VehicleRepositoryImp vehicleRepositoryImp;

    @Autowired
    private BillingReportRepositoryImp billingReportRepositoryImp;

    @Override
    public Ticket mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Ticket ticket = new Ticket();

        final UUID vehicleId = UUID.fromString(rs.getString("vehicle_id"));
        final Vehicle vehicle = vehicleRepositoryImp.findVehicleById(vehicleId);

        final UUID billingReportId = UUID.fromString(rs.getString("billing_report_id"));
        final BillingReport billingReport = billingReportRepositoryImp.findBillingReportById(billingReportId);

        ticket.setId(UUID.fromString(rs.getString("id")));
        ticket.setVehicle(vehicle);
        ticket.setEntryTime(LocalDateTime.parse(rs.getString("entry_time")));
        ticket.setDepartureTime(LocalDateTime.parse(rs.getString("departure_time")));
        ticket.setBillingReport(billingReport);

        return ticket;
    }
}
