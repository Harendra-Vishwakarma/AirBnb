package com.airbnb.dto.request;

import com.airbnb.model.HotelContactInfo;
import lombok.Data;

@Data
public class HotelRequestDto {
    private String name;
    private String[] photos;
    private String[] amenities;
    private Boolean status;
    private HotelContactInfo contactInfo;
}
