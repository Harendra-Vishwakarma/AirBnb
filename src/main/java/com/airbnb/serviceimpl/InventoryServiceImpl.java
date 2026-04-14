package com.airbnb.serviceimpl;

import com.airbnb.model.Inventory;
import com.airbnb.model.Room;
import com.airbnb.repository.InventoryRepository;
import com.airbnb.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional
    @Override
    public void createInventory(Room room, LocalDate start, LocalDate end) {
        List<Inventory> inventories = new ArrayList<>();
        for (LocalDate today = start; !today.isAfter(end); today = today.plusDays(1)) {
            Inventory inventory = new Inventory();
            inventory.setRoom(room);
            inventory.setHotel(room.getHotel());
            inventory.setCity(room.getHotel().getCity());
            inventory.setBookedRooms(0);
            inventory.setTotalRooms(room.getTotalRooms());
            inventory.setAvailableRooms(room.getTotalRooms());
            inventory.setDate(today);
            inventory.setPrice(room.getBasePrice());
            inventory.setSurgeFactor(BigDecimal.ONE);
            inventory.setClosed(false);
            inventories.add(inventory);
        }
        inventoryRepository.saveAll(inventories);

    }

    @Transactional
    @Override
    public void bookRoom(Room room, LocalDate start, LocalDate end, int roomsNeeded) {

        //fetch Inventory from start and enddate
        List<Inventory> inventories = inventoryRepository.findInventoryForUpdate(room, start, end);
        for (Inventory inventory : inventories) {
            if (inventory.getAvailableRooms() < roomsNeeded) {
                throw new RuntimeException("Rooms not available for date: " + inventory.getDate());
            }
        }
        //update afterValidation
        for (Inventory inventory : inventories) {
            inventory.setAvailableRooms(inventory.getAvailableRooms() - roomsNeeded);
            inventory.setBookedRooms(inventory.getBookedRooms() + roomsNeeded);
        }

        inventoryRepository.saveAll(inventories);


    }

    @Transactional
    @Override
    public void cancelBook(Room room, LocalDate start, LocalDate end, int rooms) {
        List<Inventory> inventories =
                inventoryRepository.findByRoomAndDateBetween(room, start, end);

        for (Inventory inv : inventories) {
            inv.setAvailableRooms(inv.getAvailableRooms() + rooms);
            inv.setBookedRooms(inv.getBookedRooms() - rooms);
        }

        inventoryRepository.saveAll(inventories);

    }

    @Transactional
    public void closeInventory(Room room, LocalDate date) {
        Inventory inv = inventoryRepository
                .findByRoomAndDate(room, date)
                .orElseThrow();

        inv.setClosed(true);
        inventoryRepository.save(inv);
    }
}
