package com.airbnb.serviceimpl;

import com.airbnb.model.Booking;
import com.airbnb.model.BookingStatus;
import com.airbnb.model.Inventory;
import com.airbnb.model.Room;
import com.airbnb.repository.BookingRepository;
import com.airbnb.repository.InventoryRepository;
import com.airbnb.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private final InventoryRepository inventoryRepository;
    private final BookingRepository bookingRepository;
    private Booking booking;

    @Override
    public void CreateBooking(Room room, LocalDate start, LocalDate end, int rooms) {

        //Step1: Lock the inventory
        List<Inventory> inventories = inventoryRepository.findInventoryForUpdate(room, start, end);

        //step2: validate the inventory
        for (Inventory inventory : inventories) {
            if (inventory.getAvailableRooms() < rooms) {
                throw new RuntimeException("Room not available!");
            }
        }
        //Step3:reserve the inventory
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Inventory inventory : inventories) {
            inventory.setBookedRooms(inventory.getBookedRooms() + rooms);
            inventory.setAvailableRooms(inventory.getAvailableRooms() - rooms);
            BigDecimal price = inventory.getPrice()
                    .multiply(inventory.getSurgeFactor());

            totalAmount = totalAmount.add(price.multiply(BigDecimal.valueOf(rooms)));
        }
        inventoryRepository.saveAll(inventories);
        //Step5: Create Payment
        Booking booking = new Booking();
        booking.setHotel(room.getHotel());
        booking.setRoom(room);
        booking.setCheckedInDate(start);
        booking.setCheckOutDate(end);
        booking.setRoomsBooked(rooms);
        booking.setTotalPrice(totalAmount);
        booking.setBookingStatus(BookingStatus.PENDING);

        bookingRepository.save(booking);

        // Step 6: Payment
//        Payment payment = paymentService.processPayment(
//                booking.getId(),
//                calculateAmount(inventories, rooms)
//        );

//        if (payment.getStatus() == FAILED) {
        //cancelBooking(booking)
//            throw new RuntimeException("Payment failed");
//        }

        // Step 7: Confirm booking
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);
    }

    @Override
    public Booking cancelBooking(Booking booking) {
        //Step1: Lock the inventory
        List<Inventory> inventories = inventoryRepository.findInventoryForUpdate(booking.getRoom(), booking.getCheckedInDate(), booking.getCheckOutDate());
        //Step2: RollBack the inventory
        for (Inventory inv : inventories) {
            inv.setAvailableRooms(inv.getAvailableRooms() + booking.getRoomsBooked());
            inv.setBookedRooms(inv.getBookedRooms() - booking.getRoomsBooked());
        }
        inventoryRepository.saveAll(inventories);
        booking.setBookingStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
        return booking;
    }
}
