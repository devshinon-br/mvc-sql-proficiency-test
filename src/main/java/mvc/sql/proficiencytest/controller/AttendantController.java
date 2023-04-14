package mvc.sql.proficiencytest.controller;

import mvc.sql.proficiencytest.model.Attendant;
import mvc.sql.proficiencytest.model.dto.AttendantDTO;
import mvc.sql.proficiencytest.model.mapper.AttendantMapper;
import mvc.sql.proficiencytest.service.AttendantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AttendantController {

    private final AttendantService attendantService;
    private final AttendantMapper mapper;

    @Autowired
    public AttendantController(final AttendantService attendantService, final AttendantMapper mapper) {
        this.attendantService = attendantService;
        this.mapper = mapper;
    }

    @GetMapping("/attendant")
    public String registerAttendant(final Model model) {
        model.addAttribute("attendantDTO", new AttendantDTO());
        return "attendant/attendant";
    }

    @PostMapping("/attendant/save")
    public String saveAttendant(@ModelAttribute("attendantDTO") @Valid final AttendantDTO attendantDTO,
                                final BindingResult bindingResult,
                                final Model model) {
        if (bindingResult.hasErrors()) {
            return "attendant/attendant";
        }

        final Attendant attendant = mapper.toEntity(attendantDTO);
        attendantService.createAttendant(attendant);

        return "redirect:/attendant/list";
    }

    @GetMapping("/attendant/list")
    public String listAttendants(final Model model) {
        final List<Attendant> attendants = attendantService.listAttendants();
        model.addAttribute("attendants", attendants);
        return "attendant/attendant-list";
    }
}
