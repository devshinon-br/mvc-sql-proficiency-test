package mvc.sql.proficiencytest.model.dto;

import lombok.Getter;
import lombok.Setter;
import mvc.sql.proficiencytest.model.BillingReport;
import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BillingReportDTO {

    @Autowired
    private TicketRepository ticketRepository;

    @NotNull
    private LocalDateTime initialDate;
    @NotNull
    private LocalDateTime finalDate;
    @NotNull
    private List<UUID> tickets;

    public BillingReport toBillingReport() {
        final List<Ticket> ticketList = ticketRepository.findTicketsByListId(tickets);

        return new BillingReport(
                this.getInitialDate(),
                this.getFinalDate(),
                ticketList
        );
    }
}
