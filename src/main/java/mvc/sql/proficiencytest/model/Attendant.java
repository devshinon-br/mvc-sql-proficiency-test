package mvc.sql.proficiencytest.model;

import lombok.Getter;
import lombok.Setter;
import mvc.sql.proficiencytest.model.dto.VehicleDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Attendant {
    private UUID id;
    private String name;

    public Vehicle registerVehicle(final VehicleDTO dto) {
        return new Vehicle(dto);
    }

    public void registerDepartureTime(final Ticket ticket) {
        ticket.setDepartureTime(LocalDateTime.now());
    }

}
