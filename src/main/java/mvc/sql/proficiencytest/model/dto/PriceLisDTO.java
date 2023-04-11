package mvc.sql.proficiencytest.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PriceLisDTO {
    private BigDecimal pricePerHour;
    private BigDecimal pricePerAdditionalHour;
    private BigDecimal singlePrice;
}
