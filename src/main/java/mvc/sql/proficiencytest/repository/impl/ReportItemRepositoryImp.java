package mvc.sql.proficiencytest.repository.impl;

import mvc.sql.proficiencytest.model.ReportItem;
import mvc.sql.proficiencytest.repository.ReportItemRepository;
import mvc.sql.proficiencytest.repository.rowmapper.ReportItemRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportItemRepositoryImp implements ReportItemRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ReportItemRowMapper reportRowMapper;

    @Autowired
    public ReportItemRepositoryImp(final JdbcTemplate jdbcTemplate,
                                   final ReportItemRowMapper reportRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.reportRowMapper = reportRowMapper;
    }

    @Override
    public List<ReportItem> findTotalValueAndTotalTimeForEachLicensePlate() {
        final String sql = "SELECT v.license_plate, SUM(extract(epoch from (t.departure_time - t.entry_time))) as total_time, SUM(t.total_value) as total_value FROM vehicle v LEFT JOIN ticket t ON t.vehicle_id = v.id WHERE t.departure_time IS NOT NULL AND t.total_value IS NOT NULL AND t.entry_time >= NOW() - INTERVAL '24 hours' GROUP BY v.license_plate";
        return jdbcTemplate.query(sql, reportRowMapper);
    }
}
