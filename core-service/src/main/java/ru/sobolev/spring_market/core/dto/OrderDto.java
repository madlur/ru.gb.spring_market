package ru.sobolev.spring_market.core.dto;

import lombok.Data;
import ru.sobolev.spring_market.api.dto.OrderItemDto;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private String username;
    private List<OrderItemDto> items;
    private Integer totalPrice;
    private String address;
    private String phone;
}
