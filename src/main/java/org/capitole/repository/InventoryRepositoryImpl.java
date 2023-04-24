package org.capitole.repository;

import com.opencsv.bean.CsvToBeanBuilder;
import org.capitole.model.InventoryStock;
import org.capitole.model.Product;
import org.capitole.model.Size;
import org.capitole.model.csv.ProductCSV;
import org.capitole.model.csv.SizeCSV;
import org.capitole.model.csv.StockCSV;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InventoryRepositoryImpl implements InventoryRepository {

    private static final String PRODUCT_FILE_PATH = "/product.csv";

    private static final String PRODUCT_STOCK_PATH = "/stock.csv";

    private static final String PRODUCT_SIZE_PATH = "/size.csv";

    @Override
    public InventoryStock getInventory() throws Exception {

        try {

            InputStream is = getClass().getResourceAsStream(PRODUCT_FILE_PATH);
            InputStreamReader isr = new InputStreamReader(is);

            InputStream is1 = getClass().getResourceAsStream(PRODUCT_STOCK_PATH);
            InputStreamReader isr2 = new InputStreamReader(is1);

            InputStream is2 = getClass().getResourceAsStream(PRODUCT_SIZE_PATH);
            InputStreamReader isr3 = new InputStreamReader(is2);


            // Function to create products
            Function<ProductCSV, Product> productFunction = productCSV ->
                 new Product(productCSV.getId(), productCSV.getSequence(), new HashMap<>());

            List<ProductCSV> productCSVList = new CsvToBeanBuilder<ProductCSV>(isr).withType(ProductCSV.class).build().parse();

            List<StockCSV> stockCSVSList = new CsvToBeanBuilder<StockCSV>(isr2).withType(StockCSV.class).build().parse();

            List<SizeCSV> sizeCSVList = new CsvToBeanBuilder<SizeCSV>(isr3).withType(SizeCSV.class).build().parse();

            Map<Integer, Product> productsMap = productCSVList.stream()
                    .map(productFunction)
                    .collect(Collectors.toMap(Product::getId, Function.identity()));

            Map<Integer, Integer> stockMap =
                    stockCSVSList.stream().collect(Collectors.toMap(StockCSV::getSizeId, StockCSV::getQuantity));

            return new InventoryStock(
                    sizeCSVList.stream()
                            .reduce(
                                    productsMap,
                                    (pMap, sizeCSV) -> mergeSize(pMap, sizeCSV, stockMap),
                                    (accMap, combMap) -> {
                                        accMap.putAll(combMap);
                                        return accMap;
                                    }));
        } catch (Exception ex) {
            throw new Exception("An error has occurred " + ex);
        }

    }

    private Map<Integer, Product> mergeSize(
            Map<Integer, Product> productMap, SizeCSV sizeCSV, Map<Integer, Integer> stockMap) {

        Product product = productMap.get(sizeCSV.getProductId());
        Integer stockPerSize = Optional.ofNullable(stockMap.get(sizeCSV.getId())).orElse(0);
        product.getMapSize().put(sizeCSV.getId(), new Size(sizeCSV.getId(), sizeCSV.getProductId(), sizeCSV.getBackSoon(), sizeCSV.getSpecial(), stockPerSize));
        return productMap;
    }
}
