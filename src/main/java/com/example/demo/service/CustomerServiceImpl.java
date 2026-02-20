package com.example.demo.service;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;
import com.example.demo.repo.CustomerRepository;
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
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {

        if (customerRepository.existsByCusName(customerDTO.getCusName())) {
            return VarList.RSP_DUPLICATED;
        }

        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customerRepository.save(customer);

        return VarList.RSP_SUCCESS;
    }

    @Override
    public String updateCustomer(CustomerDTO customerDTO) {

        if (!customerRepository.existsById(customerDTO.getCusID())) {
            return VarList.RSP_NO_DATA_FOUND;
        }

        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customerRepository.save(customer);

        return VarList.RSP_SUCCESS;
    }

    @Override
    public String deleteCustomer(int cusID) {

        if (!customerRepository.existsById(cusID)) {
            return VarList.RSP_NO_DATA_FOUND;
        }

        customerRepository.deleteById(cusID);

        return VarList.RSP_SUCCESS;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(int cusID) {

        return customerRepository.findById(cusID)
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .orElse(null);
    }
}
