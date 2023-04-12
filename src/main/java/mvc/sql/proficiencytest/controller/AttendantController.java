package mvc.sql.proficiencytest.controller;

import mvc.sql.proficiencytest.model.Attendant;
import mvc.sql.proficiencytest.model.dto.AttendantDTO;
import mvc.sql.proficiencytest.repository.AttendantRepository;
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

    @Autowired
    private AttendantRepository attendantRepository;

    @GetMapping("/attendant")
    public String registerAttendant(final Model model) {
        model.addAttribute("attendantDTO", new AttendantDTO());
        return "attendant";
    }

    @PostMapping("/attendant/save")
    public String saveAttendant(@ModelAttribute("attendantDTO") @Valid final AttendantDTO attendantDTO,
                                final BindingResult bindingResult,
                                final Model model) {
        if (bindingResult.hasErrors()) {
            return "attendant";
        }

        attendantRepository.createAttendant(attendantDTO.toAttendant());
        return "redirect:/attendant/list";
    }

    @GetMapping("/attendant/list")
    public String listAttendants(final Model model) {
        final List<Attendant> attendants = attendantRepository.listAttendants();
        model.addAttribute("attendants", attendants);
        return "attendant-list";
    }
}
