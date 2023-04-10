package mvc.sql.proficiencytest.repository;

import mvc.sql.proficiencytest.model.Ticket;

import java.util.UUID;

public interface TicketRepository {
    Ticket findTicketById(final UUID id);
}
