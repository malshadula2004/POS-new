package com.example.demo.controller;

import com.example.demo.dto.ItemDTO;
import com.example.demo.service.ItemService;
import com.example.demo.util.VarList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/getAll")
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveItem(@RequestBody ItemDTO dto) {
        String res = itemService.saveItem(dto);
        return res.equals(VarList.RSP_SUCCESS) ?
                ResponseEntity.ok(res) :
                ResponseEntity.status(400).body(res);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateItem(@RequestBody ItemDTO dto) {
        String res = itemService.updateItem(dto);
        return res.equals(VarList.RSP_SUCCESS) ?
                ResponseEntity.ok(res) :
                ResponseEntity.status(404).body(res);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable int id) {
        String res = itemService.deleteItem(id);
        return res.equals(VarList.RSP_SUCCESS) ?
                ResponseEntity.ok(res) :
                ResponseEntity.status(404).body(res);
    }
}
