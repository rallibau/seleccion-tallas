package com.rallibau.tallas.domain.productSize;

import java.util.List;

public interface ProductSizeRepository {
    void save(ProductSize stockEntry);

    List<ProductSize> getAllProductSize();

}
