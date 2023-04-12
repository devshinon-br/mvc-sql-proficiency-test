package mvc.sql.proficiencytest.model.mapper;

import mvc.sql.proficiencytest.model.Vehicle;
import mvc.sql.proficiencytest.model.VehicleModel;
import mvc.sql.proficiencytest.model.dto.VehicleDTO;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {
    public Vehicle toEntity(final VehicleDTO dto) {
        return new Vehicle(
                dto.getLicensePlate(),
                VehicleModel.valueOf(dto.getModel()),
                dto.getColor()
        );
    }
}
