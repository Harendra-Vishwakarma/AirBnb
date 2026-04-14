package com.airbnb.serviceimpl;

import com.airbnb.dto.request.RoomRequestDto;
import com.airbnb.dto.response.RoomResponseDto;
import com.airbnb.service.RoomService;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    @Override
    public RoomResponseDto createNewRoom(Long hotelId, RoomRequestDto roomRequestDto) {

        //TODO: when room is created the create the inventory for next 365 days
        return null;
    }

    @Override
    public List<RoomResponseDto> getAllRoomsByHotel(Long hotelId) {
        return List.of();
    }

    @Override
    public RoomResponseDto getRoomById(Long roomId) {
        return null;
    }

    @Override
    public void deleteRoomById(Long roomId) {

    }
}
