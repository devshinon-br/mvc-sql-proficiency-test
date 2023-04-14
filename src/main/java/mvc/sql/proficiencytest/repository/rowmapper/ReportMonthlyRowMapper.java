package mvc.sql.proficiencytest.repository.rowmapper;

import mvc.sql.proficiencytest.model.ReportMonthly;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class ReportMonthlyRowMapper implements RowMapper<ReportMonthly> {

    @Override
    public ReportMonthly mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final ReportMonthly reportMonthly = new ReportMonthly();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        final String departureDate = rs.getString("departure_date");
        if (departureDate != null) {
            final LocalDateTime date = Timestamp.valueOf(departureDate).toLocalDateTime();
            reportMonthly.setDepartureDate(date.format(formatter));
        }

        reportMonthly.setTotalVehicles(rs.getLong("num_vehicles"));
        reportMonthly.setTotalValue(rs.getBigDecimal("total_value"));

        return reportMonthly;
    }
}
