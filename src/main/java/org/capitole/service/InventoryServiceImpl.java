package org.capitole.service;

import org.capitole.model.InventoryStock;
import org.capitole.model.Product;
import org.capitole.model.Size;
import org.capitole.repository.InventoryRepository;

import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryServiceImpl implements  InventoryService{

    private InventoryRepository inventoryRepository;

    @Inject
    public InventoryServiceImpl(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Product> getInventory() throws Exception {

        try {

            InventoryStock inventory = inventoryRepository.getInventory();

            return inventory.getMapProducts().values().stream()
                    .filter(this::shouldBeShown)
                    .sorted(Comparator.comparing(Product::getSequence))
                    .collect(Collectors.toList());
        } catch (FileNotFoundException fnfe){
            throw new FileNotFoundException("File not found exception " + fnfe);
        } catch (Exception ex) {
            throw new Exception("An error has occurred " + ex);
        }
    }

    private boolean shouldBeShown(Product product) {

        int inStockBackSoon = 0;
        int specialsWithStockOrBackSoon = 0;
        int nonSpecialWithStockOrBackSoon = 0;
        int specials = 0;

        List<Size> sizeList = new ArrayList<>(product.getMapSize().values());

        for(Size size : sizeList){
            boolean hasStockOrBackSoon = size.getStock() > 0 || size.getBackSoon();

            if (hasStockOrBackSoon) {
                inStockBackSoon++;
            }
            if (hasStockOrBackSoon && Boolean.TRUE.equals(size.getSpecial())) {
                specialsWithStockOrBackSoon++;
            }
            if (hasStockOrBackSoon && Boolean.TRUE.equals(!size.getSpecial())) {
                nonSpecialWithStockOrBackSoon++;
            }
            if (Boolean.TRUE.equals(size.getSpecial())) {
                specials++;
            }

        }

        if (specials == 0 && inStockBackSoon > 0) {
            return true;
        } else return specials > 0
                && specialsWithStockOrBackSoon > 0
                && nonSpecialWithStockOrBackSoon > 0;
    }
}
