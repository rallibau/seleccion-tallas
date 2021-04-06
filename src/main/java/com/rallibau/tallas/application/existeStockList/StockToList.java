package com.rallibau.tallas.application.existeStockList;

import com.rallibau.tallas.domain.productSize.ProductSize;
import com.rallibau.tallas.domain.productSize.ProductSizeId;
import com.rallibau.tallas.domain.productSize.ProductSizeRepository;
import com.rallibau.tallas.domain.productSize.SizeSystem;
import com.rallibau.tallas.domain.stockEntry.Quantity;
import com.rallibau.tallas.domain.stockEntry.SizeId;
import com.rallibau.tallas.domain.stockEntry.StockEntry;
import com.rallibau.tallas.domain.stockEntry.StockEntryRepository;
import com.rallibau.tallas.infrastructure.productSize.InMemoryProductSizeRepository;
import com.rallibau.tallas.infrastructure.stockEntry.InMemoryStockEntryRepository;

import java.util.*;

public class StockToList {
    private final StockEntryRepository stockEntryRepository;
    private final ProductSizeRepository productSizeRepository;

    private static final ArrayList<ProductSize> productSizeEntrada = new ArrayList<>(
            Arrays.asList(new ProductSize(new ProductSizeId(1L), new SizeSystem("123")),
                    new ProductSize(new ProductSizeId(2L), new SizeSystem("345")),
                    new ProductSize(new ProductSizeId(3L), new SizeSystem("321")),
                    new ProductSize(new ProductSizeId(4L), new SizeSystem("444")),
                    new ProductSize(new ProductSizeId(5L), new SizeSystem("444")))
    );

    private static final ArrayList<StockEntry> stockEntryEntrada = new ArrayList<>(
            Arrays.asList(new StockEntry(new SizeId(2L), new Quantity(34L)),
                    new StockEntry(new SizeId(3L), new Quantity(15L)),
                    new StockEntry(new SizeId(4L), new Quantity(3L)),
                    new StockEntry(new SizeId(5L), new Quantity(2L)))
    );

    public StockToList(StockEntryRepository stockEntryRepository, ProductSizeRepository productSizeRepository) {
        this.stockEntryRepository = stockEntryRepository;
        this.productSizeRepository = productSizeRepository;
    }

    public void loadData() {
        stockEntryEntrada.forEach(stockEntryRepository::save);
        productSizeEntrada.forEach(productSizeRepository::save);
    }



    public List<ProductSize> listarTallas() {
        List<ProductSize> allProductSize = productSizeRepository.getAllProductSize();
        HashMap<String, List<ProductSize>> mapStockBySizeSystem = clasificarPorSizeSystem(allProductSize);
        List<ProductSize> resultado = filtrarProductosConStock(mapStockBySizeSystem);
        Collections.sort(resultado);

        return resultado;
    }


    private List<ProductSize> filtrarProductosConStock(HashMap<String, List<ProductSize>> mapStockBySizeSystem) {
        List<ProductSize> resultado = new ArrayList<>();
        for (Map.Entry<String, List<ProductSize>> listaAux : mapStockBySizeSystem.entrySet()) {
            anyadirProductoConMaximoInventario(listaAux.getValue()).ifPresent(resultado::add);
        }
        return resultado;
    }

    private Optional<ProductSize> anyadirProductoConMaximoInventario(List<ProductSize> listaProducto) {
        Optional<ProductSize> productSizeMaximoStock = Optional.empty();
        Long maxQuantity=0L;
        for(ProductSize productSize : listaProducto){
            Optional<StockEntry> stockEntry = stockEntryRepository.search(new SizeId(productSize.id().value()));
            if(stockEntry.isPresent() && stockEntry.get().quantity().value() > maxQuantity){
                productSizeMaximoStock = Optional.of(productSize);
                maxQuantity = stockEntry.get().quantity().value();
            }
        }
        return productSizeMaximoStock;
    }




    private HashMap<String, List<ProductSize>> clasificarPorSizeSystem(List<ProductSize> allProductSize) {
        HashMap<String, List<ProductSize>> mapStockBySizeSystem = new HashMap<>();
        for (ProductSize productSize : allProductSize) {
            List<ProductSize> listaAux = mapStockBySizeSystem.computeIfAbsent(productSize.sizeSystem().valueSoreted(), k -> new ArrayList<>());
            listaAux.add(productSize);
        }
        return mapStockBySizeSystem;
    }
}
