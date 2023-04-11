package mvc.sql.proficiencytest.repository.impl;

import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.repository.TicketRepository;
import mvc.sql.proficiencytest.repository.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.UUID;

public class TicketRepositoryImp implements TicketRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Ticket findTicketById(final UUID id) {
        if (id != null) {
            final String sql = "SELECT id, vehicle_id, billingReport_id, entry_time, departure_time FROM ticket WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new TicketMapper());
        }

        return null;
    }

    @Override
    public void createTicket(final Ticket ticket) {

    }

    @Override
    public void updateTicket(final Ticket ticket) {

    }

    @Override
    public void deletePriceList(final UUID id) {

    }

    @Override
    public List<Ticket> findTicketsByListId(final List<UUID> ids) {
        if (ids != null && !ids.isEmpty()) {
            final String sql = "SELECT id, vehicle_id, billingReport_id, entry_time, departure_time FROM ticket WHERE id IN(?)";
            final Object[] idsArray = ids.toArray();
            return jdbcTemplate.query(sql, idsArray, new TicketMapper());
        }

        return null;
    }

    @Override
    public List<Ticket> findAllBillingReportTickets(final UUID billingReportId) {
        if (billingReportId != null) {
            final String sql = "SELECT id, vehicle_id, billingReport_id, entry_time, departure_time FROM ticket WHERE billingReport_id = ?";
            return jdbcTemplate.query(sql, new TicketMapper());
        }

        return null;
    }
}
