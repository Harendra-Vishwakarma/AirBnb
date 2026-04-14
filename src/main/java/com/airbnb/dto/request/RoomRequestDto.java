package com.airbnb.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomRequestDto {

    private String type;

    private BigDecimal basePrice;

    private String[] amenities;

    private String[] photos;

    private Integer totalCount;

    private Integer capacity;

}
