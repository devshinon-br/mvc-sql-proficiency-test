package mvc.sql.proficiencytest.service;
import mvc.sql.proficiencytest.model.PriceList;
import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public BigDecimal getTheTotaValueCalculation(final PriceList priceList, final Ticket ticket) {
        final Duration diff = Duration.between(ticket.getEntryTime(), ticket.getDepartureTime());

        if (ticket.getEntryTime().getDayOfWeek() == DayOfWeek.SATURDAY) {
            return this.calculateTotalValue(diff, priceList.getSinglePrice(), BigDecimal.ZERO);
        }

        return this.calculateTotalValue(
                diff,
                priceList.getPricePerHour(),
                priceList.getPricePerAdditionalHour()
        );
    }

    private BigDecimal calculateTotalValue(final Duration diff,
                                           final BigDecimal fixedValue,
                                           final BigDecimal accumulationValue){
        if (diff.toMinutes() <= 60) {
            return fixedValue;
        }

        long hours = diff.toHours() - 1;
        long minutes = diff.toMinutes() % 60;

        if (minutes > 0) {
            hours++;
        }

        return fixedValue.add(accumulationValue.multiply(BigDecimal.valueOf(hours)));
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
}
