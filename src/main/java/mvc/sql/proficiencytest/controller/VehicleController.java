package mvc.sql.proficiencytest.controller;

import mvc.sql.proficiencytest.model.Vehicle;
import mvc.sql.proficiencytest.model.dto.VehicleDTO;
import mvc.sql.proficiencytest.repository.VehicleRepository;
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
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("/vehicle")
    public String registerVehicle(Model model) {
        model.addAttribute("vehicleDTO", new VehicleDTO());
        return "vehicle";
    }

    @PostMapping("/vehicle/save")
    public String saveVehicle(@ModelAttribute("vehicleDTO") @Valid final VehicleDTO vehicleDTO,
                                final BindingResult bindingResult,
                                final Model model) {
        if (bindingResult.hasErrors()) {
            return "vehicle";
        }

        vehicleRepository.createVehicle(vehicleDTO.toVehicle());
        return "redirect:/vehicle/list";
    }

    @GetMapping("/vehicle/list")
    public String listVehicles(final Model model) {
        final List<Vehicle> attendants = vehicleRepository.listVehicles();
        model.addAttribute("vehicles", attendants);
        return "vehicle-list";
    }

}
