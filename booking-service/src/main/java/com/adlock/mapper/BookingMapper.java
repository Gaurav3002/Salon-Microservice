package com.adlock.mapper;

import com.adlock.dto.BookingDto;
import com.adlock.model.Booking;

public class BookingMapper {


    public static BookingDto toDTO(Booking  booking){
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setCustomerId(booking.getCustomerId());
        bookingDto.setStatus(booking.getStatus());
        bookingDto.setEndTime(booking.getEndTime());
        bookingDto.setStartTime(booking.getStartTime());
        bookingDto.setSalonId(booking.getSalonId());
        bookingDto.setServiceIds(booking.getServiceIds());
        return bookingDto;
    }
}
