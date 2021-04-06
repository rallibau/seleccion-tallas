package com.rallibau.tallas.domain.productSize;

public class ProductSize implements Comparable<ProductSize> {
    private final ProductSizeId id;
    private final SizeSystem sizeSystem;

    public ProductSize(ProductSizeId id, SizeSystem sizeSystem) {
        this.id = id;
        this.sizeSystem = sizeSystem;
    }

    public ProductSizeId id() {
        return id;
    }

    public SizeSystem sizeSystem() {
        return sizeSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof ProductSize)) {
            return false;
        }
        return this.sizeSystem.equals(((ProductSize) o).sizeSystem());
    }


    @Override
    public int compareTo(ProductSize o) {
        if (o == null) {
            return 1;
        }
        return this.id().value().compareTo(o.id().value());
    }
}
