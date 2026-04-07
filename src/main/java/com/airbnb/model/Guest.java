package com.airbnb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")// here is @joinColumn use
    private User user;

    @Column(name = "guest_name")
    private String name;

    private Integer age;

    private String email;

    private String phone_number;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "guests", fetch = FetchType.LAZY)
    private Set<Booking> bookings;

}
