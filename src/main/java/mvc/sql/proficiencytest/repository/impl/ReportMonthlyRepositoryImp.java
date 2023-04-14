package mvc.sql.proficiencytest.repository.impl;

import mvc.sql.proficiencytest.model.ReportMonthly;
import mvc.sql.proficiencytest.repository.ReportMonthlyRepository;
import mvc.sql.proficiencytest.repository.rowmapper.ReportMonthlyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportMonthlyRepositoryImp implements ReportMonthlyRepository {

    @Autowired
    private ReportMonthlyRowMapper reportMonthlyRowMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ReportMonthly> findNumVehiclesAndTotalValueInMonth() {
        final String sql =
                "SELECT DATE_TRUNC('day', t.departure_time) as departure_date, " +
                "COUNT(DISTINCT v.id) as num_vehicles, " +
                "SUM(t.total_value) as total_value " +
                "FROM vehicle v " +
                "LEFT JOIN ticket t ON t.vehicle_id = v.id " +
                "WHERE t.departure_time IS NOT NULL " +
                "AND t.total_value IS NOT NULL AND t.departure_time >= NOW() - INTERVAL '1 MONTH' " +
                "GROUP BY departure_date ORDER BY departure_date ASC";

        return jdbcTemplate.query(sql, reportMonthlyRowMapper);
    }
}
