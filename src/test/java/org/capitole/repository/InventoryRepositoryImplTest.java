package org.capitole.repository;

import org.capitole.model.InventoryStock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InventoryRepositoryImplTest {

    @InjectMocks
    private InventoryRepositoryImpl inventoryRepository;

    @Test
    void testGetInventoryReturnSuccess() throws Exception {
        InventoryStock inventoryStock = inventoryRepository.getInventory();

        Assertions.assertNotNull(inventoryStock);
        Assertions.assertEquals(5, inventoryStock.getMapProducts().size());

    }

}