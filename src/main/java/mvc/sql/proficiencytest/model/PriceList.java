package mvc.sql.proficiencytest.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class PriceList {
    private UUID id = UUID.randomUUID();
    private BigDecimal pricePerHour;
    private BigDecimal pricePerAdditionalHour;
    private BigDecimal singlePrice;
}
