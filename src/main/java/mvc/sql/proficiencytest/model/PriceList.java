package mvc.sql.proficiencytest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceList {
    private UUID id = UUID.randomUUID();
    private BigDecimal pricePerHour;
    private BigDecimal pricePerAdditionalHour;
    private BigDecimal singlePrice;
}
