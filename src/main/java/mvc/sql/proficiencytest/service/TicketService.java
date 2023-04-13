package mvc.sql.proficiencytest.service;
import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;


    public long calculateLengthOfStay(final LocalDateTime entryTime, final LocalDateTime departureTime) {
        if (this.validateDepartureTimeAndEntryTime(entryTime, departureTime)) {
            return Duration.between(entryTime, departureTime).toHours();
        }
        return 0;
    }

    public boolean validateDepartureTimeAndEntryTime(final LocalDateTime entryTime, final LocalDateTime departureTime) {
        return (entryTime != null && departureTime != null)
                && departureTime.isAfter(entryTime);
    }

    public Ticket findTicketById(final UUID id) {
        return ticketRepository.findTicketById(id);
    }

    public Ticket findTicketWithoutDepartureTime(final UUID vehicleId) {
        return ticketRepository.findTicketWithoutDepartureTime(vehicleId);
    }

    public List<Ticket> listTickets() {
        return ticketRepository.listTickets();
    }

    public void createTicket(final Ticket ticket) {
        ticketRepository.createTicket(ticket);
    }

    public void updateTicket(final Ticket ticket) {
        ticketRepository.updateTicket(ticket);
    }

    public void deleteTicket(final UUID id) {
        ticketRepository.deleteTicket(id);
    }

    public List<Ticket> findTicketsByListId(final List<UUID> ids) {
        return ticketRepository.findTicketsByListId(ids);
    }

    public List<Ticket> findAllBillingReportTickets(final UUID billingReportId) {
        return ticketRepository.findAllBillingReportTickets(billingReportId);
    }
}
