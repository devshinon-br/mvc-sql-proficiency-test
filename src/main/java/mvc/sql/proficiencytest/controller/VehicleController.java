package mvc.sql.proficiencytest.controller;

import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.model.Vehicle;
import mvc.sql.proficiencytest.model.dto.VehicleDTO;
import mvc.sql.proficiencytest.model.mapper.VehicleMapper;
import mvc.sql.proficiencytest.service.TicketService;
import mvc.sql.proficiencytest.service.VehicleService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VehicleController {
    private final VehicleService vehicleService;
    private final TicketService ticketService;
    private final VehicleMapper mapper;

    @Autowired
    public VehicleController(final VehicleService vehicleService,
                             final TicketService ticketService,
                             final VehicleMapper mapper) {
        this.vehicleService = vehicleService;
        this.ticketService = ticketService;
        this.mapper = mapper;
    }

    @GetMapping("/vehicle")
    public String registerVehicle(Model model) {
        model.addAttribute("vehicleDTO", new VehicleDTO());
        return "vehicle/vehicle";
    }

    @PostMapping("/vehicle/save")
    @ResponseBody
    public Map<String, Object> saveVehicle(@ModelAttribute("vehicleDTO") @Valid final VehicleDTO vehicleDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            Vehicle vehicle = vehicleService.findVehicleByLicensePlate(vehicleDTO.getLicensePlate());
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            if (vehicle == null) {
                vehicle = mapper.toEntity(vehicleDTO);
                vehicleService.createVehicle(vehicle);
            }

            Ticket ticket = ticketService.findTicketWithoutDepartureTime(vehicle.getId());
            if (ticket != null) {
                response.put("errorMessage", "Já existe uma entrada sem saída correspondente registrada com a data: " + ticket.getEntryTime().format(formatter));
                return response;
            }

            ticket = new Ticket(vehicle, LocalDateTime.now(), null, BigDecimal.ZERO);
            ticketService.createTicket(ticket);

            response.put("entryTime", ticket.getEntryTime().format(formatter));
            response.put("licensePlate", vehicle.getLicensePlate());

            return response;
        } catch (final Exception e) {
            response.put("errorMessage", "Erro inesperado!");
            return response;
        }
    }

    @GetMapping("/vehicle/list")
    public String listVehicles(final Model model) {
        final List<Pair<Vehicle, Ticket>> pairList = vehicleService.findVehiclesWhereDepartureTimeIsNull();

        model.addAttribute("vehicles", pairList);

        return "vehicle/vehicle-list";
    }


}
