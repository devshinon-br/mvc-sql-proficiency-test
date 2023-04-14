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
    private final JdbcTemplate jdbcTemplate;
    private final TicketRowMapper ticketRowMapper;

    @Autowired
    public TicketRepositoryImp(JdbcTemplate jdbcTemplate, TicketRowMapper ticketRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.ticketRowMapper = ticketRowMapper;
    }

    @Override
    public Ticket findTicketById(final UUID id) {
        if (id != null) {
            final String sql = "SELECT id, vehicle_id, entry_time, departure_time, total_value FROM ticket WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, ticketRowMapper);
        }

        return null;
    }

    @Override
    public Ticket findTicketWithoutDepartureTime(final UUID vehicleId) {
        try {
            final String sql = "SELECT id, vehicle_id, entry_time, departure_time, total_value FROM ticket WHERE vehicle_id = ? AND entry_time IS NOT NULL AND departure_time IS NULL ORDER BY entry_time DESC";
            List<Ticket> ticketList = jdbcTemplate.query(sql, new Object[]{vehicleId}, ticketRowMapper);
            if (!ticketList.isEmpty()) {
                return ticketList.get(0);
            }

            return null;
        } catch (final Exception e) {
            return null;
        }
    }

    @Override
    public List<Ticket> listTickets() {
        final String sql = "SELECT id, vehicle_id, entry_time, departure_time, total_value FROM ticket";
        return jdbcTemplate.query(sql, ticketRowMapper);
    }

    @Override
    public void createTicket(final Ticket ticket) {
        if (ticket != null) {
            final String sql = "INSERT INTO ticket (id, vehicle_id, entry_time, departure_time, total_value) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(
                    sql,
                    ticket.getId(),
                    ticket.getVehicle().getId(),
                    ticket.getEntryTime(),
                    ticket.getDepartureTime(),
                    ticket.getTotalValue()
            );
        }
    }

    @Override
    public void updateTicket(final Ticket ticket) {
        if (ticket != null) {
            final String sql = "UPDATE ticket SET entry_time = ?, departure_time = ?, total_value = ? WHERE id = ?";
            jdbcTemplate.update(sql, ticket.getEntryTime(), ticket.getDepartureTime(), ticket.getTotalValue(), ticket.getId());
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
            final String sql = "SELECT id, vehicle_id, entry_time, departure_time, total_value FROM ticket WHERE id IN(?)";
            final Object[] idsArray = ids.toArray();
            return jdbcTemplate.query(sql, idsArray, ticketRowMapper);
        }

        return null;
    }
}
