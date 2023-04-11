package mvc.sql.proficiencytest.repository;

import mvc.sql.proficiencytest.model.PriceList;

import java.util.UUID;

public interface PriceListRepository {
    PriceList findPriceListById(final UUID id);

    void createPriceList(final PriceList priceList);

    void updatePriceList(final PriceList priceList);

    void deletePriceList(final UUID id);
}
