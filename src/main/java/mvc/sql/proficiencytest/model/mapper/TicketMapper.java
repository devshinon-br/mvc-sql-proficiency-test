package mvc.sql.proficiencytest.model.mapper;

import mvc.sql.proficiencytest.model.PriceList;
import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.model.Vehicle;
import mvc.sql.proficiencytest.model.dto.TicketDTO;
import mvc.sql.proficiencytest.service.PriceListService;
import mvc.sql.proficiencytest.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private PriceListService priceListService;

    public Ticket toEntity(final TicketDTO dto) {
        final Vehicle vehicle = vehicleService.findVehicleById(dto.getVehicleId());

        return new Ticket(
                vehicle,
                dto.getEntryTime(),
                dto.getDepartureTime(),
                dto.getTotalValue()
        );
    }

}
