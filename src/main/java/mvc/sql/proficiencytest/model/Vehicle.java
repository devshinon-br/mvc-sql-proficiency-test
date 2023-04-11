package mvc.sql.proficiencytest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Vehicle {
    private UUID id;
    private String licensePlate;
    private VehicleModel model;
    private String color;

    public Vehicle(final String licensePlate,
                   final VehicleModel model,
                   final String color) {
        this.id = UUID.randomUUID();
        this.licensePlate = licensePlate;
        this.model = model;
        this.color = color;
    }
}
