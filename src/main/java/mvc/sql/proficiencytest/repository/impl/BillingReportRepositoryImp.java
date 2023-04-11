package mvc.sql.proficiencytest.repository.impl;

import mvc.sql.proficiencytest.model.BillingReport;
import mvc.sql.proficiencytest.repository.BillingReportRepository;
import mvc.sql.proficiencytest.repository.mapper.BillingReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

public class BillingReportRepositoryImp implements BillingReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public BillingReport findBillingReportById(final UUID id) {
        if (id != null) {
            final String sql = "SELECT id, initial_date, final_date FROM billing_report WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BillingReportMapper());
        }

        return null;
    }

    @Override
    public void createBillingReport(final BillingReport billingReport) {

    }

    @Override
    public void updateBillingReport(final BillingReport billingReport) {

    }

    @Override
    public void deleteBillingReport(final UUID id) {

    }
}
