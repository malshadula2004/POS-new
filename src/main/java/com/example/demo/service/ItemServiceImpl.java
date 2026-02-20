package com.example.demo.service;

import com.example.demo.dto.ItemDTO;
import com.example.demo.entity.Item;
import com.example.demo.repo.ItemRepository;
import com.example.demo.util.VarList;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    @Override
    public String saveItem(ItemDTO itemDTO) {
        if (itemRepository.existsByItemName(itemDTO.getItemName())) {
            return VarList.RSP_DUPLICATED;
        }
        Item item = modelMapper.map(itemDTO, Item.class);
        itemRepository.save(item);
        return VarList.RSP_SUCCESS;
    }

    @Override
    public String updateItem(ItemDTO itemDTO) {
        if (!itemRepository.existsById(itemDTO.getItemID())) {
            return VarList.RSP_NO_DATA_FOUND;
        }
        Item item = modelMapper.map(itemDTO, Item.class);
        itemRepository.save(item);
        return VarList.RSP_SUCCESS;
    }

    @Override
    public String deleteItem(int itemID) {
        if (!itemRepository.existsById(itemID)) {
            return VarList.RSP_NO_DATA_FOUND;
        }
        itemRepository.deleteById(itemID);
        return VarList.RSP_SUCCESS;
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(item -> modelMapper.map(item, ItemDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ItemDTO getItemById(int itemID) {
        return itemRepository.findById(itemID)
                .map(item -> modelMapper.map(item, ItemDTO.class))
                .orElse(null);
    }
}
