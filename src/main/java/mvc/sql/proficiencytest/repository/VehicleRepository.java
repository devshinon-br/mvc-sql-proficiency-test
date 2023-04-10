package mvc.sql.proficiencytest.repository;

import mvc.sql.proficiencytest.model.Vehicle;

import java.util.UUID;

public interface VehicleRepository {
    Vehicle findVehicleById(final UUID id);
}
