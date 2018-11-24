package com.oims.futureprogram.service;

import com.oims.futureprogram.model.Inventories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InventoriesService {

    Page<Inventories> getInventories(Pageable pageable);

    Inventories getOneInventories(Long inventoriesId);

    List<Inventories> getInventoriesByFormRequestId(Long formrequestId);

    Inventories createInventories(Inventories inventories, Long formrequestId);

    Inventories updateInventories(Long formrequestId, Long inventoriesId, Inventories inventoriesrequest);

    void deleteInventories(Long formrequestId, Long inventoriesId);

    void deleteOneInventories(Long inventoriesId);
}
