package com.airbnb.dto.response;

import com.airbnb.model.HotelContactInfo;
import lombok.Data;

@Data
public class HotelResponseDto {
    private Long id;
    private String name;
    private String[] photos;
    private String[] amenities;
    private Boolean status;
    private HotelContactInfo contactInfo;
}
