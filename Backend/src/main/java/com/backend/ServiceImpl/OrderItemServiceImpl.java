//package com.backend.ServiceImpl;
//
//import com.backend.DTO.OrderItemDto;
//import com.backend.Mapper.OrderItemMapper;
//import com.backend.Model.OrderItem;
//import com.backend.Repository.OrderItemRepository;
//import com.backend.Service.OrderItemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class OrderItemServiceImpl implements OrderItemService {
//    @Autowired
//    private OrderItemRepository orderItemRepository;
//
//    @Autowired
//    private OrderItemMapper orderItemMapper;
//
//    @Override
//    public List<OrderItemDto> getAllOrderItems() {
//        List<OrderItem> orderItems = orderItemRepository.findAll();
//        return orderItems.stream()
//                .map(orderItemMapper::toOrderItemDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public OrderItemDto getOrderItemById(Long id) {
//        OrderItem orderItem = orderItemRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("OrderItem not found"));
//        return orderItemMapper.toOrderItemDTO(orderItem);
//    }
//
//    @Override
//    public OrderItemDto createOrderItem(OrderItemDto orderItemDTO) {
//        OrderItem orderItem = orderItemMapper.toOrderItem(orderItemDTO);
//        orderItemRepository.save(orderItem);
//        return orderItemMapper.toOrderItemDTO(orderItem);
//    }
//
//    @Override
//    public OrderItemDto updateOrderItem(Long id, OrderItemDto orderItemDTO) {
//        OrderItem orderItem = orderItemRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("OrderItem not found"));
//        orderItemMapper.toOrderItem(orderItemDTO); // Update fields
//        orderItemRepository.save(orderItem);
//        return orderItemMapper.toOrderItemDTO(orderItem);
//    }
//
//    @Override
//    public void deleteOrderItem(Long id) {
//        orderItemRepository.deleteById(id);
//    }
//}
//
//
