package mvc.sql.proficiencytest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BillingReport {
    private UUID id;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private List<Ticket> tickets;

    public BillingReport(final LocalDateTime initialDate,
                         final LocalDateTime finalDate,
                         final List<Ticket> tickets) {
        this.id = UUID.randomUUID();
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.tickets = tickets;
    }
}
