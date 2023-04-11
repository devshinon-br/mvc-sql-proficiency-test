package mvc.sql.proficiencytest.model.dto;

import lombok.Getter;
import lombok.Setter;
import mvc.sql.proficiencytest.model.BillingReport;
import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.model.Vehicle;
import mvc.sql.proficiencytest.repository.BillingReportRepository;
import mvc.sql.proficiencytest.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TicketDTO {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BillingReportRepository billingReportRepository;

    @NotNull
    private UUID vehicleId;
    private UUID billingReportId;
    @NotNull
    private LocalDateTime entryTime;
    @NotNull
    private LocalDateTime departureTime;

    public Ticket toTicket() {
        final Vehicle vehicle = vehicleRepository.findVehicleById(this.getVehicleId());
        final BillingReport billingReport = billingReportRepository.findBillingReportById(this.getBillingReportId());

        return new Ticket(
                vehicle,
                billingReport,
                this.getEntryTime(),
                this.getDepartureTime()
        );
    }
}
