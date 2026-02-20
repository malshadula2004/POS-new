package com.example.demo.service;

import com.example.demo.dto.ItemDTO;
import java.util.List;

public interface ItemService {

    String saveItem(ItemDTO itemDTO);

    String updateItem(ItemDTO itemDTO);

    String deleteItem(int itemID);

    List<ItemDTO> getAllItems();

    ItemDTO getItemById(int itemID);
}
