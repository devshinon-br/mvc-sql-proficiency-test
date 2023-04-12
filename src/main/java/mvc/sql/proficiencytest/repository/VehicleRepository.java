package mvc.sql.proficiencytest.repository;

import mvc.sql.proficiencytest.model.Vehicle;

import java.util.List;
import java.util.UUID;

public interface VehicleRepository {
    Vehicle findVehicleById(final UUID id);

    List<Vehicle> listVehicles();

    void createVehicle(final Vehicle vehicle);

    void updateVehicle(final Vehicle vehicle);

    void deleteVehicle(final UUID id);
}
