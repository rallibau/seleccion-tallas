package com.rallibau.tallas.domain.stockEntry;

import java.util.Optional;

public interface StockEntryRepository {
    void save(StockEntry stockEntry);

    Optional<StockEntry> search(SizeId id);

}
