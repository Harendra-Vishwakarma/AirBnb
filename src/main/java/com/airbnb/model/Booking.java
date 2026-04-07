package com.airbnb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id",nullable = false)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id",nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status",nullable = false)
    private BookingStatus bookingStatus;

    @Column(nullable = false)
    private Integer roomsCount;

    @Column(name = "checked_in_date",nullable = false)
    private LocalDateTime CheckedInDate;

    @Column(name = "checked_out_date",nullable = false)
    private LocalDateTime checkOutDate;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "booking_guest"
    ,joinColumns = @JoinColumn(name = "booking_id"),
    inverseJoinColumns = @JoinColumn(name="guest_id"))
    private Set<Guest> guests;




}
