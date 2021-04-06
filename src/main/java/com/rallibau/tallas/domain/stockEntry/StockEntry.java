package com.rallibau.tallas.domain.stockEntry;

public class StockEntry implements Comparable<StockEntry>{
    private final SizeId sizeId;
    private final Quantity quantity;

    public StockEntry(SizeId sizeId, Quantity quantity) {
        this.sizeId = sizeId;
        this.quantity = quantity;
    }

    public SizeId id(){
        return sizeId;
    }

    public Quantity quantity(){
        return quantity;
    }

    @Override
    public int compareTo(StockEntry o) {
        if(o == null){
            return 1;
        }
        return this.id().value().compareTo(o.id().value());
    }
}
