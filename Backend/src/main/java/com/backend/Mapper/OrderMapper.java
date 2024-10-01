package com.backend.Mapper;


import com.backend.DTO.OrderDto;
import com.backend.Model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toOrderDTO(Order order);
    Order toOrder(OrderDto orderDTO);
}
