package mvc.sql.proficiencytest.repository.rowmapper;

import mvc.sql.proficiencytest.model.PriceList;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class PriceListRowMapper implements RowMapper<PriceList> {
    @Override
    public PriceList mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final PriceList priceList = new PriceList();

        priceList.setId(UUID.fromString(rs.getString("id")));
        priceList.setPricePerHour(rs.getBigDecimal("price_per_hour"));
        priceList.setPricePerAdditionalHour(rs.getBigDecimal("price_per_additional_hour"));
        priceList.setSinglePrice(rs.getBigDecimal("single_price"));
        priceList.setStatus(rs.getBoolean("status"));

        return priceList;
    }
}
