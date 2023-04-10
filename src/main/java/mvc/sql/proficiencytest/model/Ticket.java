package mvc.sql.proficiencytest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mvc.sql.proficiencytest.model.dto.TicketDTO;
import mvc.sql.proficiencytest.repository.impl.VehicleRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Autowired
    private VehicleRepositoryImp vehicleRepositoryImp;

    private UUID id = UUID.randomUUID();
    private Vehicle vehicle;
    private BillingReport billingReport;
    private LocalDateTime entryTime = LocalDateTime.now();
    private LocalDateTime departureTime;

    public Ticket(final TicketDTO dto) {
        final Vehicle vehicle = vehicleRepositoryImp.findVehicleById(dto.getVehicleId());
        this.setVehicle(vehicle);
        this.setEntryTime(dto.getEntryTime());
        this.setDepartureTime(dto.getDepartureTime());
    }

    public long calculateLengthOfStay(final LocalDateTime entryTime, final LocalDateTime departureTime) {
        if (this.validateDepartureTimeAndEntryTime(entryTime, departureTime)) {
            return Duration.between(entryTime, departureTime).toHours();
        }
        return 0;
    }

    public boolean validateDepartureTimeAndEntryTime(final LocalDateTime entryTime, final LocalDateTime departureTime) {
        return (entryTime != null && departureTime != null)
                && departureTime.isAfter(entryTime);
    }
}
