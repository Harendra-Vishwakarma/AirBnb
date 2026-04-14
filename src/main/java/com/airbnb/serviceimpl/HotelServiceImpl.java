package com.airbnb.serviceimpl;

import com.airbnb.dto.request.HotelRequestDto;
import com.airbnb.dto.response.HotelResponseDto;
import com.airbnb.service.HotelService;

import java.util.List;

public class HotelServiceImpl implements HotelService {
    @Override
    public HotelResponseDto createHotel(HotelRequestDto hotelRequestDto) {
        return null;
    }

    @Override
    public HotelResponseDto updateHotel(Long hotelId, HotelRequestDto hotelRequestDto) {
        return null;
    }

    @Override
    public List<HotelResponseDto> getHotels() {
        return List.of();
    }

    @Override
    public void deleteHotel(Long hotelId) {

    }
}
