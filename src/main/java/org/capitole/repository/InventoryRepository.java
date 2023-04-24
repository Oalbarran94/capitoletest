package org.capitole.repository;

import org.capitole.model.InventoryStock;

public interface InventoryRepository {
    InventoryStock getInventory() throws Exception;
}
