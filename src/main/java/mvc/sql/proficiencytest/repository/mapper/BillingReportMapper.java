package mvc.sql.proficiencytest.repository.mapper;

import mvc.sql.proficiencytest.model.BillingReport;
import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BillingReportMapper implements RowMapper<BillingReport> {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public BillingReport mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final BillingReport billingReport = new BillingReport();

        final UUID id = UUID.fromString(rs.getString("id"));
        final List<Ticket> tickets = ticketRepository.findAllBillingReportTickets(id);

        billingReport.setId(id);
        billingReport.setTickets(tickets);
        billingReport.setFinalDate(LocalDateTime.parse(rs.getString("final_date")));
        billingReport.setInitialDate(LocalDateTime.parse(rs.getString("initial_date")));

        return billingReport;
    }
}
