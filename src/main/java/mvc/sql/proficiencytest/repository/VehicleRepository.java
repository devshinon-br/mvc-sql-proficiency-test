package mvc.sql.proficiencytest.repository;

import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.model.Vehicle;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.UUID;

public interface VehicleRepository {
    Vehicle findVehicleById(final UUID id);

    Vehicle findVehicleByLicensePlate(final String licensePlate);

    List<Pair<Vehicle, Ticket>> findVehiclesWhereDepartureTimeIsNull();

    List<Vehicle> listVehicles();

    void createVehicle(final Vehicle vehicle);

    void updateVehicle(final Vehicle vehicle);

    void deleteVehicle(final UUID id);
}
