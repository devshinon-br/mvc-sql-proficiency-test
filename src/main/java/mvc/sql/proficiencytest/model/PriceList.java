package mvc.sql.proficiencytest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PriceList {
    private UUID id;
    private BigDecimal pricePerHour;
    private BigDecimal pricePerAdditionalHour;
    private BigDecimal singlePrice;
    private boolean status;

    public PriceList(final BigDecimal pricePerHour,
                     final BigDecimal pricePerAdditionalHour,
                     final BigDecimal singlePrice,
                     final boolean status) {
        this.id = UUID.randomUUID();
        this.pricePerHour = pricePerHour;
        this.pricePerAdditionalHour =  pricePerAdditionalHour;
        this.singlePrice = singlePrice;
        this.status = status;
    }
}
