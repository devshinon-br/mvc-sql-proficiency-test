package mvc.sql.proficiencytest.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReportMonthly {
    private String departureDate;
    private long totalVehicles;
    private BigDecimal totalValue;
}
