package mvc.sql.proficiencytest.controller;

import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.model.dto.TicketDTO;
import mvc.sql.proficiencytest.model.mapper.TicketMapper;
import mvc.sql.proficiencytest.service.TicketService;
import mvc.sql.proficiencytest.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class TicketController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketMapper mapper;

    @GetMapping("/ticket")
    public String registerTicket(final Model model) {
        model.addAttribute("vehicles", vehicleService.listVehicles());
        model.addAttribute("ticketDTO", new TicketDTO());
        return "ticket";
    }

    @PostMapping("/ticket/save")
    public String saveTicket(@ModelAttribute("ticketDTO") @Valid final TicketDTO ticketDTO,
                             final BindingResult result) {
        if (result.hasErrors()) {
            return "ticket";
        }

        final Ticket ticket = mapper.toEntity(ticketDTO);
        ticketService.createTicket(ticket);

        return "redirect:/attendant/list";
    }
}
