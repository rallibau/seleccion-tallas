package com.rallibau.tallas.domain.productSize;

public class ProductSizeMother {
    public static ProductSize create(ProductSizeId id,
                                     SizeSystem sizeSystem) {
        return new ProductSize(id, sizeSystem);
    }



    public static ProductSize random() {
        return create(ProductSizeIdMother.random(), SizeSystemMother.random());
    }

}
