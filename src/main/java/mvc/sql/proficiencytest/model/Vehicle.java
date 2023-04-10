package mvc.sql.proficiencytest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mvc.sql.proficiencytest.model.dto.VehicleDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Vehicle {
    private UUID id = UUID.randomUUID();
    private String licensePlate;
    private VehicleModel model;
    private String color;

    public Vehicle(final VehicleDTO dto) {
        this.setLicensePlate(dto.getLicensePlate());
        this.setModel(dto.getModel());
        this.setColor(dto.getColor());
    }
}
