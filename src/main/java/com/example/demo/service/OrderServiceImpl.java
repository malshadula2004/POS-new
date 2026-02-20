package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderDetailDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.repo.CustomerRepository;
import com.example.demo.repo.ItemRepository;
import com.example.demo.repo.OrderRepository;
import com.example.demo.util.VarList;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    @Override
    public String saveOrder(OrderDTO orderDTO) {
        Customer customer = customerRepository.findById(orderDTO.getCusID()).orElse(null);
        if(customer == null) return VarList.RSP_NO_DATA_FOUND;

        Order order = new Order();
        order.setCustomer(customer);

        List<OrderDetail> details = new ArrayList<>();
        for(OrderDetailDTO d : orderDTO.getOrderDetails()) {
            Item item = itemRepository.findById(d.getItemID()).orElse(null);
            if(item == null) continue;
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setItem(item);
            detail.setQty(d.getQty());
            detail.setPrice(d.getPrice());
            details.add(detail);
        }

        order.setOrderDetails(details);
        orderRepository.save(order);
        return VarList.RSP_SUCCESS;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> list = new ArrayList<>();
        orderRepository.findAll().forEach(o -> {
            OrderDTO dto = new OrderDTO();
            dto.setCusID(o.getCustomer().getCusID());

            List<OrderDetailDTO> details = new ArrayList<>();
            o.getOrderDetails().forEach(d -> {
                details.add(new OrderDetailDTO(d.getItem().getItemID(), d.getQty(), d.getPrice()));
            });
            dto.setOrderDetails(details);
            list.add(dto);
        });
        return list;
    }
}
