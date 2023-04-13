package mvc.sql.proficiencytest.service;

import mvc.sql.proficiencytest.model.BillingReport;
import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.repository.BillingReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BillingReportService {

    private final BillingReportRepository billingReportRepository;

    @Autowired
    public BillingReportService(BillingReportRepository billingReportRepository) {
        this.billingReportRepository = billingReportRepository;
    }

    public BigDecimal calculateBilling(final LocalDateTime initialDate, final LocalDateTime finalDate) {
        final Duration interval = Duration.between(initialDate, finalDate);

        if ( interval.toDays() > 1 && interval.toDays() <= 31) {
            return this.calculateMonthlyBilling();
        }

        if (interval.toDays() == 1 || interval.toHours() <= 24) {
            return this.calculateDailyBilling();
        }

        return new BigDecimal(0);
    }

    private BigDecimal calculateDailyBilling() {

        // TODO: do the calculation
        return new BigDecimal(0);
    }

    private BigDecimal calculateMonthlyBilling() {

        // TODO: do the calculation
        return new BigDecimal(0);
    }

    public BillingReport findBillingReportById(final UUID id) {
        return billingReportRepository.findBillingReportById(id);
    }

    public void createBillingReport(final BillingReport billingReport) {
        billingReportRepository.createBillingReport(billingReport);
    }


    public void updateBillingReport(final BillingReport billingReport) {
        billingReportRepository.updateBillingReport(billingReport);
    }

    public void deleteBillingReport(final UUID id) {
        billingReportRepository.deleteBillingReport(id);
    }
}
