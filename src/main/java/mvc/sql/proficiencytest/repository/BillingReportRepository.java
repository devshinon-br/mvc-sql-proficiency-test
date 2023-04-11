package mvc.sql.proficiencytest.repository;

import mvc.sql.proficiencytest.model.BillingReport;

import java.util.UUID;

public interface BillingReportRepository {
    BillingReport findBillingReportById(final UUID id);

    void createBillingReport(final BillingReport billingReport);

    void updateBillingReport(final BillingReport billingReport);

    void deleteBillingReport(final UUID id);
}
