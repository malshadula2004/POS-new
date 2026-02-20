package com.example.demo.service;

import com.example.demo.dto.CustomerDTO;
import java.util.List;

public interface CustomerService {

    String saveCustomer(CustomerDTO customerDTO);

    String updateCustomer(CustomerDTO customerDTO);

    String deleteCustomer(int cusID);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(int cusID);
}
