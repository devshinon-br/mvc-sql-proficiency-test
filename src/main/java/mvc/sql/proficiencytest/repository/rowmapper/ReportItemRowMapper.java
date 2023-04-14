package mvc.sql.proficiencytest.repository.rowmapper;

import mvc.sql.proficiencytest.model.ReportItem;
import mvc.sql.proficiencytest.utils.LenghtOfStay;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static mvc.sql.proficiencytest.utils.LenghtOfStay.getLenghtOfStay;

@Component
public class ReportItemRowMapper implements RowMapper<ReportItem> {
    @Override
    public ReportItem mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final ReportItem report = new ReportItem();

        report.setLicensePlate(rs.getString("license_plate"));
        report.setTotalValue(rs.getBigDecimal("total_value"));

        final long totalTime = rs.getBigDecimal("total_time").longValue();

        report.setTotalTime(getLenghtOfStay(totalTime));

        return report;
    }
}
