package mvc.sql.proficiencytest.repository.rowmapper;

import mvc.sql.proficiencytest.model.ReportDaily;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static mvc.sql.proficiencytest.utils.LenghtOfStay.getLenghtOfStay;

@Component
public class ReportDailyRowMapper implements RowMapper<ReportDaily> {
    @Override
    public ReportDaily mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final ReportDaily report = new ReportDaily();
        final long totalTime = rs.getBigDecimal("total_time").longValue();

        report.setLicensePlate(rs.getString("license_plate"));
        report.setTotalValue(rs.getBigDecimal("total_value"));
        report.setTotalTime(getLenghtOfStay(totalTime));

        return report;
    }
}
