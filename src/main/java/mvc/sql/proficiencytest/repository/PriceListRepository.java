package mvc.sql.proficiencytest.repository;

import mvc.sql.proficiencytest.model.PriceList;

import java.util.List;
import java.util.UUID;

public interface PriceListRepository {
    PriceList findPriceListById(final UUID id);

    List<PriceList> listPriceLists();

    void createPriceList(final PriceList priceList);

    void updatePriceList(final PriceList priceList);

    List<PriceList> getPriceListActived();

    void deletePriceList(final UUID id);
}
