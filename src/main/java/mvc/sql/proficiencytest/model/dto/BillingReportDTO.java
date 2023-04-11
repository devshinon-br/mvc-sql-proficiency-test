package mvc.sql.proficiencytest.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BillingReportDTO {
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private List<UUID> tickets;
}
