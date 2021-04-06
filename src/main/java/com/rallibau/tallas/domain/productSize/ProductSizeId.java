package com.rallibau.tallas.domain.productSize;

public class ProductSizeId {
    private final Long value;

    public ProductSizeId(Long value) {
        this.value = value;
    }

    public Long value(){
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductSizeId that = (ProductSizeId) o;
        return value.equals(that.value);
    }
}
