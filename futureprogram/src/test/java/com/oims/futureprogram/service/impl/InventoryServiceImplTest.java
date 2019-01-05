package com.oims.futureprogram.service.impl;

import com.oims.futureprogram.MockingObject.FakeObjectFactory;
import com.oims.futureprogram.model.Inventory;
import com.oims.futureprogram.repository.InventoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InventoryServiceImplTest {
    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Mock
    private InventoryRepository inventoryRepository;

    @Test
    public void getListInventoryTest() {
        when(inventoryRepository.findAll()).thenReturn(Arrays.asList(
                Inventory.builder().id((long)1).build(),
                Inventory.builder().id((long)2).build()
        ));

        List<Inventory> inventories = inventoryService.getListInventory();

        Assert.assertEquals(inventories, inventoryRepository.findAll());
    }

    @Test
    public void createInventoryTest() {
        Inventory inventory = FakeObjectFactory.getFakeInventory();

        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);
        Inventory returned = inventoryService.createInventory(inventory);

        assertEquals(inventory, returned);
    }
}