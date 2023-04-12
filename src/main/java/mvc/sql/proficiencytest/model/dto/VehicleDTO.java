package mvc.sql.proficiencytest.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VehicleDTO {

    @NotNull
    private String licensePlate;
    @NotNull
    private String model;
    @NotNull
    private String color;

}
