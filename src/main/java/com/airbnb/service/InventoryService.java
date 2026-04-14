package com.airbnb.service;

import com.airbnb.model.Room;

import java.time.LocalDate;

public interface InventoryService {

    void createInventory(Room room, LocalDate start, LocalDate end);

    void bookRoom(Room room , LocalDate start, LocalDate end, int roomsNeeded);

    void cancelBook(Room room, LocalDate start, LocalDate end, int roomsNeeded);

    public void closeInventory(Room room, LocalDate date);
}
