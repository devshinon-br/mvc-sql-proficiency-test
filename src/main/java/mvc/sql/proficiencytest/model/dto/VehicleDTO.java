package mvc.sql.proficiencytest.model.dto;

import lombok.Getter;
import lombok.Setter;
import mvc.sql.proficiencytest.model.VehicleModel;

import java.time.LocalDateTime;

@Getter
@Setter
public class VehicleDTO {
    private String licensePlate;
    private VehicleModel model;
    private String color;
    private LocalDateTime entryTime;
}
