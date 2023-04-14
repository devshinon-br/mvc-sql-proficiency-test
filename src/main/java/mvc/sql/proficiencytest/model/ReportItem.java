package mvc.sql.proficiencytest.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ReportItem {
    private String licensePlate;
    private String totalTime;
    private BigDecimal totalValue;
}
