package mvc.sql.proficiencytest.model.mapper;

import mvc.sql.proficiencytest.model.BillingReport;
import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.model.Vehicle;
import mvc.sql.proficiencytest.model.dto.TicketDTO;
import mvc.sql.proficiencytest.service.BillingReportService;
import mvc.sql.proficiencytest.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private BillingReportService reportService;

    public Ticket toEntity(final TicketDTO dto) {
        final Vehicle vehicle = vehicleService.findVehicleById(dto.getVehicleId());
        final BillingReport billingReport = reportService.findBillingReportById(dto.getBillingReportId());

        return new Ticket(
                vehicle,
                billingReport,
                dto.getEntryTime(),
                dto.getDepartureTime()
        );
    }

}
