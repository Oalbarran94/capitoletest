package org.capitole.service;

import org.capitole.model.Product;

import java.util.List;

public interface InventoryService {

    List<Product> getInventory() throws Exception;
}
