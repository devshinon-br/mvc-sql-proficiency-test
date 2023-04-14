package mvc.sql.proficiencytest.controller;

import mvc.sql.proficiencytest.model.ReportItem;
import mvc.sql.proficiencytest.service.ReportItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ReportItemController {
    @Autowired
    private ReportItemService reportItemService;

    @GetMapping("/report/daily")
    public String showReport(final Model model) {
        final List<ReportItem> items = reportItemService.findTotalValueAndTotalTimeForEachLicensePlate();
        final BigDecimal totalValue = items.stream()
                        .map(ReportItem::getTotalValue)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("items", items);
        model.addAttribute("totalValue", totalValue);

        return "report/report";
    }
}
