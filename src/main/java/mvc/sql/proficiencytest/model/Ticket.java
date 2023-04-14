package mvc.sql.proficiencytest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Ticket {

    private UUID id;
    private Vehicle vehicle;
    private LocalDateTime entryTime;
    private LocalDateTime departureTime;
    private BigDecimal totalValue;

    public Ticket(final Vehicle vehicle,
                  final LocalDateTime entryTime,
                  final LocalDateTime departureTime,
                  final BigDecimal totalValue) {
        this.id = UUID.randomUUID();
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.departureTime = departureTime;
        this.totalValue = totalValue;
    }
}
