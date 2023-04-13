package mvc.sql.proficiencytest.controller;

import mvc.sql.proficiencytest.model.mapper.TicketMapper;
import mvc.sql.proficiencytest.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TicketController {

    private final TicketService ticketService;

    private final TicketMapper mapper;

    @Autowired
    public TicketController(final TicketService ticketService, final TicketMapper mapper) {
        this.ticketService = ticketService;
        this.mapper = mapper;
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

}
