package mvc.sql.proficiencytest.repository.impl;

import mvc.sql.proficiencytest.model.ReportDaily;
import mvc.sql.proficiencytest.repository.ReportDailyRepository;
import mvc.sql.proficiencytest.repository.rowmapper.ReportDailyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportDailyRepositoryImp implements ReportDailyRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ReportDailyRowMapper reportRowMapper;

    @Autowired
    public ReportDailyRepositoryImp(final JdbcTemplate jdbcTemplate,
                                    final ReportDailyRowMapper reportRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.reportRowMapper = reportRowMapper;
    }

    @Override
    public List<ReportDaily> findTotalValueAndTotalTimeForEachLicensePlate() {
        final String sql =
                "SELECT v.license_plate, " +
                "SUM(extract(epoch from (t.departure_time - t.entry_time))) as total_time, " +
                "SUM(t.total_value) as total_value " +
                "   FROM vehicle v" +
                "       LEFT JOIN ticket t ON t.vehicle_id = v.id " +
                "           WHERE t.departure_time IS NOT NULL " +
                "               AND t.total_value IS NOT NULL " +
                "               AND t.departure_time >= NOW() - INTERVAL '24 hours' " +
                "               AND t.departure_time <= NOW() " +
                "           GROUP BY v.license_plate";
        return jdbcTemplate.query(sql, reportRowMapper);
    }
}
