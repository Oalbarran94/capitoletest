package org.capitole.service;

import junit.framework.TestCase;

import org.capitole.model.InventoryStock;
import org.capitole.model.Product;
import org.capitole.model.Size;
import org.capitole.repository.InventoryRepository;
import org.capitole.repository.InventoryRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceImplTest {

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Mock
    private InventoryRepositoryImpl inventoryRepository;

    @Test
    void testGetInventoryReturnSuccess() throws Exception {


        Map<Integer, Product> products =
                Map.ofEntries(
                        entry(1, new Product(1, 10, Map.of(1, new Size(1, 1, false, false, 1)))),
                        entry(2, new Product(2, 7, Map.of(1, new Size(1, 1, false, false, 1)))),
                        entry(3, new Product(3, 15, Map.of(1, new Size(1, 1, false, false, 1)))),
                        entry(4, new Product(4, 13, Map.of(1, new Size(1, 1, false, false, 1)))),
                        entry(5, new Product(5, 6, Map.of(1, new Size(1, 1, false, false, 1)))));

        when(inventoryRepository.getInventory()).thenReturn(new InventoryStock(products));

        List<Product> listProduct = inventoryService.getInventory();

        Assertions.assertNotNull(listProduct);

    }

}