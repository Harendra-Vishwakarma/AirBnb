package com.airbnb.repository;

import com.airbnb.model.Inventory;
import com.airbnb.model.Room;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface
InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByRoomAndDate(Room room, LocalDate date);

    List<Inventory> findByRoomAndDateBetween(Room room, LocalDate start, LocalDate end);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT i FROM Inventory i WHERE i.room = :room AND i.date BETWEEN :start AND :end")
    List<Inventory> findInventoryForUpdate(Room room, LocalDate start, LocalDate end);
}
