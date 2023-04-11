package mvc.sql.proficiencytest.model.dto;

import lombok.Getter;
import lombok.Setter;
import mvc.sql.proficiencytest.model.Vehicle;
import mvc.sql.proficiencytest.model.VehicleModel;

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

    public Vehicle toVehicle() {
        return new Vehicle(
                this.getLicensePlate(),
                VehicleModel.valueOf(this.getModel()),
                this.getColor()
        );
    }
}
