package mvc.sql.proficiencytest.repository;

import mvc.sql.proficiencytest.model.Ticket;

import java.util.List;
import java.util.UUID;

public interface TicketRepository {
    Ticket findTicketById(final UUID id);

    Ticket findTicketWithoutDepartureTime(final UUID vehicleId);

    List<Ticket> listTickets();

    void createTicket(final Ticket ticket);

    void updateTicket(final Ticket ticket);

    void deleteTicket(final UUID id);

    List<Ticket> findTicketsByListId(final List<UUID> ids);
}
