package mvc.sql.proficiencytest.service;

import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.model.Vehicle;
import mvc.sql.proficiencytest.repository.VehicleRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle findVehicleById(final UUID id) {
        return vehicleRepository.findVehicleById(id);
    }

    public Vehicle findVehicleByLicensePlate(final String licensePlate) {
        return vehicleRepository.findVehicleByLicensePlate(licensePlate);
    }

    public List<Pair<Vehicle, Ticket>> findVehiclesWhereDepartureTimeIsNull() {
        return vehicleRepository.findVehiclesWhereDepartureTimeIsNull();
    }

    public List<Vehicle> listVehicles() {
        return vehicleRepository.listVehicles();
    }

    public void createVehicle(final Vehicle vehicle) {
        vehicleRepository.createVehicle(vehicle);
    }

    public void updateVehicle(final Vehicle vehicle) {
        vehicleRepository.updateVehicle(vehicle);
    }

    public void deleteVehicle(final UUID id) {
        vehicleRepository.deleteVehicle(id);
    }
}
