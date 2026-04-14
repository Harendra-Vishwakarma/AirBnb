package com.airbnb.service;

import com.airbnb.model.Booking;
import com.airbnb.model.Room;

import java.time.LocalDate;

public interface BookingService {

    void CreateBooking(Room room, LocalDate start, LocalDate end, int rooms);

    Booking cancelBooking(Booking booking);
}
