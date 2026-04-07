package com.airbnb.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class HotelContactInfo {
    private String completeAddress;
    private String email;
    private String location;
    private String phoneNumber;
}
