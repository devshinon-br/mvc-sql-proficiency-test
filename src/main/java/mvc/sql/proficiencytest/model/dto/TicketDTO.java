package mvc.sql.proficiencytest.model.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TicketDTO {
    @NotNull
    private UUID vehicleId;
    private UUID priceListId;
    @NotNull
    private LocalDateTime entryTime;
    private LocalDateTime departureTime;
}
