package mvc.sql.proficiencytest.repository.impl;

import mvc.sql.proficiencytest.model.PriceList;
import mvc.sql.proficiencytest.repository.PriceListRepository;
import mvc.sql.proficiencytest.repository.mapper.PriceListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PriceListRepositoryImp implements PriceListRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public PriceList findPriceListById(final UUID id) {
        if (id != null) {
            final String sql = "SELECT id, price_per_hour, price_per_additional_hour, single_price FROM price_list WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new PriceListMapper());
        }

        return null;
    }

    @Override
    public void createPriceList(final PriceList priceList) {
        if (priceList != null) {
            final String sql = "INSERT INTO price_list (id, price_per_hour, price_per_additional_hour, single_price) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, priceList.getId(), priceList.getPricePerHour(), priceList.getPricePerAdditionalHour(), priceList.getSinglePrice());
        }
    }

    @Override
    public void updatePriceList(final PriceList priceList) {
        if (priceList != null) {
            final String sql = "UPDATE price_list price_per_hour = ?, price_per_additional_hour = ?, single_price = ? WHERE id = ?";
            jdbcTemplate.update(sql, priceList.getPricePerHour(), priceList.getPricePerAdditionalHour(), priceList.getSinglePrice() ,priceList.getId());
        }
    }

    @Override
    public void deletePriceList(final UUID id) {
        if (id != null) {
            final String sql = "DELETE FROM price_list WHERE id = ?";
            jdbcTemplate.update(sql, id);
        }
    }
}
