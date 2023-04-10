package mvc.sql.proficiencytest.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TicketDTO {
    private UUID vehicleId;
    private LocalDateTime entryTime;
    private LocalDateTime departureTime;
}
