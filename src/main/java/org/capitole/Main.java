package org.capitole;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.capitole.model.Product;
import org.capitole.repository.InventoryRepository;
import org.capitole.repository.InventoryRepositoryImpl;
import org.capitole.service.InventoryService;
import org.capitole.service.InventoryServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws Exception {

        Injector injector = Guice.createInjector(new InitializationModule());

        InventoryService inventoryService = injector.getInstance(InventoryService.class);

        List<Product> inventory = inventoryService.getInventory();

        String result = inventory.stream().map(Product::getId).map(String::valueOf).collect(Collectors.joining(","));

        System.out.println("inventory " +result);
    }
}

class InitializationModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(InventoryRepository.class).to(InventoryRepositoryImpl.class).asEagerSingleton();
        bind(InventoryService.class).to(InventoryServiceImpl.class).asEagerSingleton();
    }
}