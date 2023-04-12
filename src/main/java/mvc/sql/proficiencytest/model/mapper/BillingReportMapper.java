package mvc.sql.proficiencytest.model.mapper;

import mvc.sql.proficiencytest.model.BillingReport;
import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.model.dto.BillingReportDTO;
import mvc.sql.proficiencytest.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BillingReportMapper {

    @Autowired
    private TicketService ticketService;

    public BillingReport toEntity(final BillingReportDTO dto) {
        final List<Ticket> ticketList = ticketService.findTicketsByListId(dto.getTickets());

        return new BillingReport(
                dto.getInitialDate(),
                dto.getFinalDate(),
                ticketList
        );
    }
}
