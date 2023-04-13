package mvc.sql.proficiencytest.repository.impl;

import mvc.sql.proficiencytest.model.PriceList;
import mvc.sql.proficiencytest.repository.PriceListRepository;
import mvc.sql.proficiencytest.repository.rowmapper.PriceListRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PriceListRepositoryImp implements PriceListRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PriceListRowMapper priceListRowMapper;

    @Override
    public PriceList findPriceListById(final UUID id) {
        if (id != null) {
            final String sql = "SELECT id, price_per_hour, price_per_additional_hour, single_price, status FROM price_list WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, priceListRowMapper);
        }

        return null;
    }

    @Override
    public List<PriceList> listPriceLists() {
        final String sql = "SELECT id, price_per_hour, price_per_additional_hour, single_price, status FROM price_list";
        return jdbcTemplate.query(sql, priceListRowMapper);
    }

    @Override
    public void createPriceList(final PriceList priceList) {
        if (priceList != null) {
            final String sql = "INSERT INTO price_list (id, price_per_hour, price_per_additional_hour, single_price, status) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(
                    sql,
                    priceList.getId(),
                    priceList.getPricePerHour(),
                    priceList.getPricePerAdditionalHour(),
                    priceList.getSinglePrice(),
                    priceList.isStatus()
            );
        }
    }

    @Override
    public void updatePriceList(final PriceList priceList) {
        if (priceList != null) {
            final String sql = "UPDATE price_list SET price_per_hour = ?, price_per_additional_hour = ?, single_price = ?, status = ? WHERE id = ?";
            jdbcTemplate.update(sql, priceList.getPricePerHour(), priceList.getPricePerAdditionalHour(), priceList.getSinglePrice(), priceList.isStatus(), priceList.getId());
        }
    }

    @Override
    public List<PriceList> getPriceListActived() {
        final String sql = "SELECT id, price_per_hour, price_per_additional_hour, single_price, status FROM price_list WHERE status = TRUE";
        return jdbcTemplate.query(sql, priceListRowMapper);
    }

    @Override
    public void deletePriceList(final UUID id) {
        if (id != null) {
            final String sql = "DELETE FROM price_list WHERE id = ?";
            jdbcTemplate.update(sql, id);
        }
    }
}
