package mvc.sql.proficiencytest.controller;

import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.model.Vehicle;
import mvc.sql.proficiencytest.model.dto.VehicleDTO;
import mvc.sql.proficiencytest.model.mapper.VehicleMapper;
import mvc.sql.proficiencytest.service.TicketService;
import mvc.sql.proficiencytest.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private VehicleMapper mapper;

    @GetMapping("/vehicle")
    public String registerVehicle(Model model) {
        model.addAttribute("vehicleDTO", new VehicleDTO());
        return "vehicle/vehicle";
    }

    @PostMapping("/vehicle/save")
    public String saveVehicle(@ModelAttribute("vehicleDTO") @Valid final VehicleDTO vehicleDTO,
                                final BindingResult bindingResult,
                                final Model model) {
        try {
            final Vehicle alreadyExists = vehicleService.findVehicleByLicensePlate(vehicleDTO.getLicensePlate());

            if(alreadyExists != null) {
                final Ticket ticket = new Ticket(alreadyExists, null, LocalDateTime.now(), null);
                ticketService.createTicket(ticket);

                return "redirect:/ticket?licensePlate="+alreadyExists.getLicensePlate()+"&entryTime="+ticket.getEntryTime();
            }

            final Vehicle vehicle = mapper.toEntity(vehicleDTO);
            vehicleService.createVehicle(vehicle);

            final Ticket ticket = new Ticket(vehicle, null, LocalDateTime.now(), null);
            ticketService.createTicket(ticket);

            return "redirect:/ticket?licensePlate="+vehicle.getLicensePlate()+"&entryTime="+ticket.getEntryTime();

        } catch (final Exception e) {
            model.addAttribute("errorMessage", "Erro inesperado!");
            return "error";
        }
    }

    @GetMapping("/vehicle/list")
    public String listVehicles(final Model model) {
        final List<Vehicle> vehicles = vehicleService.listVehicles();
        model.addAttribute("vehicles", vehicles);
        return "vehicle/vehicle-list";
    }

}
