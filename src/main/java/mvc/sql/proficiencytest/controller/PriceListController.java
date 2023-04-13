package mvc.sql.proficiencytest.controller;

import mvc.sql.proficiencytest.model.PriceList;
import mvc.sql.proficiencytest.model.dto.PriceListDTO;
import mvc.sql.proficiencytest.model.mapper.PriceListMapper;
import mvc.sql.proficiencytest.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
public class PriceListController {

    @Autowired
    private PriceListService priceListService;

    @Autowired
    private PriceListMapper priceListMapper;

    @GetMapping("/pricelist")
    public String registerPriceList(final Model model) {
        model.addAttribute("priceListDTO", new PriceListDTO());

        return "pricelist/pricelist";
    }

    @PostMapping("/pricelist/save")
    public String savePriceList(@Valid @ModelAttribute("priceListDTO") final PriceListDTO priceListDTO,
                                final BindingResult result) {
        if (result.hasErrors()) {
            return "pricelist/pricelist";
        }

        final PriceList priceList = priceListMapper.toEntity(priceListDTO);
        priceListService.checkStatusTrueAndChange(priceList);
        priceListService.createPriceList(priceList);

        return "redirect:/pricelist/list";
    }

    @GetMapping("/pricelist/list")
    public String listPriceLists(final Model model) {
        final List<PriceList> priceLists = priceListService.listPriceList();
        model.addAttribute("priceLists", priceLists);

        return "pricelist/pricelist-list";
    }

    @PostMapping("/pricelist/delete")
    public String deletePriceList(@RequestParam("id") final UUID id) {
        priceListService.deletePriceList(id);
        return "redirect:/pricelist/list";
    }

    @GetMapping("/pricelist/edit")
    public String editPriceList(@RequestParam("id") final UUID id,
                                final Model model) {

        final PriceList priceList = priceListService.findPriceListById(id);

        model.addAttribute("id", id);
        model.addAttribute("priceListDTO", priceListMapper.toDto(priceList));

        return "pricelist/pricelist-edit";
    }

    @PostMapping("/pricelist/edit/save")
    public String saveEditPriceList(@RequestParam("id") final UUID id,
                                    @ModelAttribute("priceListDTO") PriceListDTO priceListDTO) {

        final PriceList priceList = priceListMapper.toEntity(priceListDTO);
        priceList.setId(id);

        priceListService.checkStatusTrueAndChange(priceList);

        priceListService.updatePriceList(priceList);

        return "redirect:/pricelist/list";
    }
}
