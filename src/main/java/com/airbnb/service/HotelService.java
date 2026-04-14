package com.airbnb.service;

import com.airbnb.dto.request.HotelRequestDto;
import com.airbnb.dto.response.HotelResponseDto;

import java.util.List;

public interface HotelService {

    HotelResponseDto createHotel(HotelRequestDto hotelRequestDto);
    HotelResponseDto updateHotel(Long hotelId,HotelRequestDto hotelRequestDto);
    List<HotelResponseDto> getHotels();
    void deleteHotel(Long hotelId);


}
