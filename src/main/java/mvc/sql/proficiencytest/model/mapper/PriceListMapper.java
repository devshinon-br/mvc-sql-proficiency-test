package mvc.sql.proficiencytest.model.mapper;

import mvc.sql.proficiencytest.model.PriceList;
import mvc.sql.proficiencytest.model.dto.PriceListDTO;
import org.springframework.stereotype.Component;

@Component
public class PriceListMapper {
    public PriceList toEntity(final PriceListDTO dto) {
        return new PriceList(
                dto.getPricePerHour(),
                dto.getPricePerAdditionalHour(),
                dto.getSinglePrice()
        );
    }
}
