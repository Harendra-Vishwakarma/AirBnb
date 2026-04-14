package com.airbnb.service;

import com.airbnb.dto.request.RoomRequestDto;
import com.airbnb.dto.response.RoomResponseDto;

import java.util.List;

public interface RoomService {

    RoomResponseDto createNewRoom(Long hotelId,RoomRequestDto roomRequestDto);

    List<RoomResponseDto> getAllRoomsByHotel(Long hotelId);

    RoomResponseDto getRoomById(Long roomId);

    void deleteRoomById(Long roomId);

}
