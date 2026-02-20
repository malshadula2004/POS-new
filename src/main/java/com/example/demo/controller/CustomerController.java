package com.example.demo.controller;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.service.CustomerService;
import com.example.demo.util.VarList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342") // frontend එකේ URL)
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/getAllCustomers")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/saveCustomer")
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerDTO dto) {
        String res = customerService.saveCustomer(dto);
        return res.equals(VarList.RSP_SUCCESS) ?
                ResponseEntity.ok(res) :
                ResponseEntity.status(400).body(res);
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<String> updateCustomer(@RequestBody CustomerDTO dto) {
        String res = customerService.updateCustomer(dto);
        return res.equals(VarList.RSP_SUCCESS) ?
                ResponseEntity.ok(res) :
                ResponseEntity.status(404).body(res);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        String res = customerService.deleteCustomer(id);
        return res.equals(VarList.RSP_SUCCESS) ?
                ResponseEntity.ok(res) :
                ResponseEntity.status(404).body(res);
    }
}
