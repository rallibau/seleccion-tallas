package com.rallibau.tallas.domain.stockEntry;

public class SizeId{
    private final Long value;

    public SizeId(Long value) {
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
        SizeId that = (SizeId) o;
        return value.equals(that.value);
    }
}
