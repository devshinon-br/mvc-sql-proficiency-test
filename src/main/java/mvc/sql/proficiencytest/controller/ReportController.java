package mvc.sql.proficiencytest.controller;

import mvc.sql.proficiencytest.model.ReportDaily;
import mvc.sql.proficiencytest.model.ReportMonthly;
import mvc.sql.proficiencytest.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/report/daily")
    public String showReportDaily(final Model model) {
        final List<ReportDaily> items = reportService.findTotalValueAndTotalTimeForEachLicensePlate();
        final BigDecimal totalValue = items.stream()
                        .map(ReportDaily::getTotalValue)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("items", items);
        model.addAttribute("totalValue", totalValue);

        return "report/daily";
    }

    @GetMapping("/report/monthly")
    public String showReportMonthly(final Model model) {
        final List<ReportMonthly> items = reportService.findNumVehiclesAndTotalValueInMonth();
        final BigDecimal totalValue = items.stream()
                .map(ReportMonthly::getTotalValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("items", items);
        model.addAttribute("totalValue", totalValue);

        return "report/monthly";
    }
}
