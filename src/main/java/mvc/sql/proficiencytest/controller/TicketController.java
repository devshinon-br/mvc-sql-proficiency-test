package mvc.sql.proficiencytest.controller;

import mvc.sql.proficiencytest.model.PriceList;
import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.model.dto.TicketDTO;
import mvc.sql.proficiencytest.service.PriceListService;
import mvc.sql.proficiencytest.service.TicketService;
import mvc.sql.proficiencytest.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TicketController {

    private final TicketService ticketService;

    private final VehicleService vehicleService;

    private final PriceListService priceListService;

    @Autowired
    public TicketController(final TicketService ticketService,
                            final VehicleService vehicleService,
                            final PriceListService priceListService) {
        this.ticketService = ticketService;
        this.vehicleService = vehicleService;
        this.priceListService = priceListService;
    }

    @GetMapping("/ticket")
    public Map<String, Object> registerTicket(@RequestParam(value = "entryTime") final LocalDateTime entryTime,
                                              @RequestParam(value = "licensePlate") final String licensePlate) {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Map<String, Object> response = new HashMap<>();
        response.put("entryTime", entryTime.format(formatter));
        response.put("licensePlate", licensePlate);
        return response;
    }

    @GetMapping("/ticket/departure-time")
    public String showTicketForm(final Model model,
                                 @RequestParam(value = "selectedLicensePlate", defaultValue = "") final String selectedLicensePlate) {
        model.addAttribute("vehicles", vehicleService.findVehiclesWhereDepartureTimeIsNull());
        model.addAttribute("ticketDTO", new TicketDTO());
        model.addAttribute("selectedLicensePlate", selectedLicensePlate);

        return "ticket/departureTime";
    }

    @PostMapping("/ticket/departure-time/save")
    public String saveTicket(@ModelAttribute("ticketDTO") @Valid final TicketDTO ticketDTO,
                             final BindingResult result,
                             final Model model) {
        if (result.hasErrors()) {
            return "ticket/departureTime";
        }

        final List<PriceList> priceList = priceListService.getPriceListActived();
        if (priceList == null || priceList.isEmpty()) {
            model.addAttribute("errorMessage", "Não há tabela de preços ativa!");
            return "error";
        }

        final Ticket ticket = ticketService.findTicketWithoutDepartureTime(ticketDTO.getVehicleId());
        ticket.setDepartureTime(ticketDTO.getDepartureTime());

        final BigDecimal totalValue = ticketService.getTheTotaValueCalculation(priceList.get(0), ticket);
        ticket.setTotalValue(totalValue);

        ticketService.updateTicket(ticket);

        return "redirect:/vehicle/list";
    }

}
