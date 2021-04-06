package com.rallibau.tallas.infrastructure.stockEntry;

import com.rallibau.tallas.domain.stockEntry.SizeId;
import com.rallibau.tallas.domain.stockEntry.StockEntry;
import com.rallibau.tallas.domain.stockEntry.StockEntryRepository;

import java.util.HashMap;
import java.util.Optional;

public class InMemoryStockEntryRepository implements StockEntryRepository {
    private final HashMap<Long, StockEntry> stockEntries = new HashMap<>();

    @Override
    public void save(StockEntry stockEntry) {
        stockEntries.put(stockEntry.id().value(), stockEntry);
    }

    @Override
    public Optional<StockEntry> search(SizeId id) {
        return Optional.ofNullable(stockEntries.get(id.value()));
    }

}
