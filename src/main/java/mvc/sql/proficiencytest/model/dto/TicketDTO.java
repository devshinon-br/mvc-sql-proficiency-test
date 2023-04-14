package mvc.sql.proficiencytest.model.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TicketDTO {
    @NotNull
    private UUID vehicleId;
    @NotNull
    private LocalDateTime entryTime;
    private LocalDateTime departureTime;
    private BigDecimal totalValue;
}
