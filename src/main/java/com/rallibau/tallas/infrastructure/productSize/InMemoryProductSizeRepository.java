package com.rallibau.tallas.infrastructure.productSize;

import com.rallibau.tallas.domain.productSize.ProductSize;
import com.rallibau.tallas.domain.productSize.ProductSizeRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryProductSizeRepository implements ProductSizeRepository {
    private final HashMap<Long, ProductSize> productSizes = new HashMap<>();

    @Override
    public void save(ProductSize stockEntry) {
        productSizes.put(stockEntry.id().value(), stockEntry);
    }


    @Override
    public List<ProductSize> getAllProductSize() {
        return new ArrayList<>(productSizes.values());
    }


}
