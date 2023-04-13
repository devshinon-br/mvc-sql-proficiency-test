package mvc.sql.proficiencytest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mvc.sql.proficiencytest.model.PriceList;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceListDTO {
    @NotNull
    private BigDecimal pricePerHour;
    @NotNull
    private BigDecimal pricePerAdditionalHour;
    @NotNull
    private BigDecimal singlePrice;
    @NotNull
    private boolean status;
}
