package existeStockList;

import com.rallibau.tallas.application.existeStockList.StockToList;
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
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockToListShould {
    private final StockEntryRepository stockEntryRepository = new InMemoryStockEntryRepository();
    private final ProductSizeRepository productSizeRepository = new InMemoryProductSizeRepository();

    @Test
    public void empty_data_not_failure() {
        StockToList stockToList = new StockToList(stockEntryRepository,productSizeRepository);
        stockToList.listarTallas();
    }

    @Test
    public void if_stock_is_empty_result_is_empty() {
        StockToList stockToList = new StockToList(stockEntryRepository,productSizeRepository);
        final ArrayList<ProductSize> productSizeEntrada = new ArrayList<>(
                Collections.singletonList(new ProductSize(new ProductSizeId(1L), new SizeSystem("123")))
        );

        productSizeEntrada.forEach(productSizeRepository::save);


        assertEquals(stockToList.listarTallas().size(),0,"Los productos listados deben ser 0"); ;
    }

    @Test
    public void if_stock_not_empty_result_is_not_empty() {

        final ArrayList<ProductSize> productSizeEntrada = new ArrayList<>(
                Collections.singletonList(new ProductSize(new ProductSizeId(1L), new SizeSystem("123")))
        );
       final ArrayList<StockEntry> stockEntryEntrada = new ArrayList<>(
               Collections.singletonList(new StockEntry(new SizeId(1L), new Quantity(34L)))
        );

        productSizeEntrada.forEach(productSizeRepository::save);
        stockEntryEntrada.forEach(stockEntryRepository::save);
        StockToList stockToList = new StockToList(stockEntryRepository,productSizeRepository);

        assertEquals(stockToList.listarTallas().size(),1,"Los productos listados deben ser 1");
    }

    @Test
    public void if_stock_quantity_is_0_result_is_0() {

        final ArrayList<ProductSize> productSizeEntrada = new ArrayList<>(
                Collections.singletonList(new ProductSize(new ProductSizeId(1L), new SizeSystem("123")))
        );
        final ArrayList<StockEntry> stockEntryEntrada = new ArrayList<>(
                Collections.singletonList(new StockEntry(new SizeId(1L), new Quantity(0L)))
        );

        productSizeEntrada.forEach(productSizeRepository::save);
        stockEntryEntrada.forEach(stockEntryRepository::save);
        StockToList stockToList = new StockToList(stockEntryRepository,productSizeRepository);

        assertEquals(stockToList.listarTallas().size(),0,"Los productos listados deben ser 0");
    }
    @Test
    public void product_with_disctint_siseSystem_are_disctint() {

        final ArrayList<ProductSize> productSizeEntrada = new ArrayList<>(
                Arrays.asList(new ProductSize(new ProductSizeId(1L), new SizeSystem("123")),
                        new ProductSize(new ProductSizeId(2L), new SizeSystem("678")))
        );
        final ArrayList<StockEntry> stockEntryEntrada = new ArrayList<>(
                Arrays.asList(new StockEntry(new SizeId(1L), new Quantity(34L)),
                        new StockEntry(new SizeId(2L), new Quantity(15L)))
        );

        productSizeEntrada.forEach(productSizeRepository::save);
        stockEntryEntrada.forEach(stockEntryRepository::save);
        StockToList stockToList = new StockToList(stockEntryRepository,productSizeRepository);

        assertEquals(stockToList.listarTallas().size(),2,"Los productos listados deben ser 2");
    }
    @Test
    public void only_list_product_with_max_stock() {

        final ArrayList<ProductSize> productSizeEntrada = new ArrayList<>(
                Arrays.asList(new ProductSize(new ProductSizeId(1L), new SizeSystem("123")),
                        new ProductSize(new ProductSizeId(2L), new SizeSystem("321")))
        );
        final ArrayList<StockEntry> stockEntryEntrada = new ArrayList<>(
                Arrays.asList(new StockEntry(new SizeId(1L), new Quantity(34L)),
                        new StockEntry(new SizeId(2L), new Quantity(15L)))
        );

        productSizeEntrada.forEach(productSizeRepository::save);
        stockEntryEntrada.forEach(stockEntryRepository::save);
        StockToList stockToList = new StockToList(stockEntryRepository,productSizeRepository);

        assertEquals(stockToList.listarTallas().get(0).id().value(),1L,"Debe listarse el producto con id 1L");
    }
}
