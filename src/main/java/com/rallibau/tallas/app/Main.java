package com.rallibau.tallas.app;

import com.rallibau.tallas.application.existeStockList.StockToList;
import com.rallibau.tallas.domain.productSize.ProductSize;
import com.rallibau.tallas.infrastructure.productSize.InMemoryProductSizeRepository;
import com.rallibau.tallas.infrastructure.stockEntry.InMemoryStockEntryRepository;

public class Main {
    public static void main(String args[]) {
        StockToList stockToList = new StockToList(new InMemoryStockEntryRepository(),new InMemoryProductSizeRepository());
        stockToList.loadData();
        boolean coma = false;
        for (ProductSize productSize : stockToList.listarTallas()) {
            if (coma) {
                System.out.print(",");
            } else {
                coma = true;
            }


            System.out.print(productSize.id().value());


        }
    }
}
