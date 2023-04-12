package mvc.sql.proficiencytest.controller;

import mvc.sql.proficiencytest.model.mapper.TicketMapper;
import mvc.sql.proficiencytest.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketMapper mapper;

    @GetMapping("/ticket")
    public String registerTicket(final Model model,
                                 @RequestParam(value = "entryTime") final LocalDateTime entryTime,
                                 @RequestParam(value = "licensePlate") final String licensePlate) {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        model.addAttribute("entryTime", entryTime.format(formatter));
        model.addAttribute("licensePlate", licensePlate);
        return "ticket/ticket";
    }

}
