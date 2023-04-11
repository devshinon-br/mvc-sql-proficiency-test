package mvc.sql.proficiencytest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mvc.sql.proficiencytest.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Ticket {
    @Autowired
    private VehicleRepository vehicleRepository;

    private UUID id;
    private Vehicle vehicle;
    private BillingReport billingReport;
    private LocalDateTime entryTime;
    private LocalDateTime departureTime;

    public Ticket(final Vehicle vehicle,
                  final BillingReport billingReport,
                  final LocalDateTime entryTime,
                  final LocalDateTime departureTime) {
        this.id = UUID.randomUUID();
        this.vehicle = vehicle;
        this.billingReport = billingReport;
        this.entryTime = entryTime;
        this.departureTime = departureTime;
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
