package mvc.sql.proficiencytest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mvc.sql.proficiencytest.model.dto.VehicleDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attendant {
    private UUID id;
    private String name;

    public Vehicle registerVehicle(final VehicleDTO dto) {
        return new Vehicle(dto);
    }

    public void registerDepartureTime(final Ticket ticket) {
        ticket.setDepartureTime(LocalDateTime.now());
    }

    public void registerEntryTime(final Ticket ticket) {
        ticket.setEntryTime(LocalDateTime.now());
    }
}
