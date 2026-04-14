package com.airbnb.dto.response;

import com.airbnb.model.Hotel;

import java.math.BigDecimal;

public class RoomResponseDto {

    private Long id;

    private Hotel hotel;

    private String type;

    private BigDecimal basePrice;

    private String[] amenities;

    private String[] photos;

    private Integer totalCount;

    private Integer capacity;

}
