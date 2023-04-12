package mvc.sql.proficiencytest.repository.impl;

import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.repository.TicketRepository;
import mvc.sql.proficiencytest.repository.rowmapper.TicketRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class TicketRepositoryImp implements TicketRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Ticket findTicketById(final UUID id) {
        if (id != null) {
            final String sql = "SELECT id, vehicle_id, billingReport_id, entry_time, departure_time FROM ticket WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new TicketRowMapper());
        }

        return null;
    }

    @Override
    public List<Ticket> listTickets() {
        final String sql = "SELECT id, vehicle_id, billingReport_id, entry_time, departure_time FROM ticket";
        return jdbcTemplate.query(sql, new TicketRowMapper());
    }

    @Override
    public void createTicket(final Ticket ticket) {
        if (ticket != null) {
            final String sql = "INSERT INTO ticket (id, vehicle_id, billing_report_id, entry_time, departure_time) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(
                    sql,
                    ticket.getId(),
                    ticket.getVehicle().getId(),
                    ticket.getBillingReport() == null ? null : ticket.getBillingReport().getId(),
                    ticket.getEntryTime(),
                    ticket.getDepartureTime()
            );
        }
    }

    @Override
    public void updateTicket(final Ticket ticket) {
        if (ticket != null) {
            final String sql = "UPDATE ticket entry_time = ?, departure_time = ? WHERE id = ?";
            jdbcTemplate.update(sql, ticket.getEntryTime(), ticket.getDepartureTime(), ticket.getId());
        }
    }

    @Override
    public void deleteTicket(final UUID id) {
        if (id != null) {
            final String sql = "DELETE FROM ticket WHERE id = ?";
            jdbcTemplate.update(sql, id);
        }
    }

    @Override
    public List<Ticket> findTicketsByListId(final List<UUID> ids) {
        if (ids != null && !ids.isEmpty()) {
            final String sql = "SELECT id, vehicle_id, billingReport_id, entry_time, departure_time FROM ticket WHERE id IN(?)";
            final Object[] idsArray = ids.toArray();
            return jdbcTemplate.query(sql, idsArray, new TicketRowMapper());
        }

        return null;
    }

    @Override
    public List<Ticket> findAllBillingReportTickets(final UUID billingReportId) {
        if (billingReportId != null) {
            final String sql = "SELECT id, vehicle_id, billingReport_id, entry_time, departure_time FROM ticket WHERE billingReport_id = ?";
            return jdbcTemplate.query(sql, new TicketRowMapper());
        }

        return null;
    }
}
