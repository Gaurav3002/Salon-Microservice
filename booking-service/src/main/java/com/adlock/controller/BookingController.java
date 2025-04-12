package com.adlock.controller;

import com.adlock.domain.BookingStatus;
import com.adlock.dto.*;
import com.adlock.mapper.BookingMapper;
import com.adlock.model.Booking;
import com.adlock.model.SalonRepot;
import com.adlock.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestParam Long salonId,@RequestBody BookingRequest request) throws Exception {
        UserDTO user = new UserDTO();
        user.setId(1L);
        SalonDto salon = new SalonDto();
        salon.setId(salonId);
        salon.setOpenTime(LocalTime.now());
        salon.setCloseTime(LocalTime.now().plusHours(12));
        Set<ServiceOfferingDto> serviceDtoset = new HashSet<>();
        ServiceOfferingDto serviceDto = new ServiceOfferingDto();
        serviceDto.setId(1L);
        serviceDto.setPrice(399);
        serviceDto.setDuration(45);
        serviceDto.setName("Hair Cut for men");
        serviceDtoset.add(serviceDto);

        Booking booking =  bookingService.createBooking(request, user, salon, serviceDtoset);

        return ResponseEntity.ok(booking);
    }

    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDto>> getBookingByCustomer(){

        List<Booking> bookings = bookingService.getBokingsByCustomer(1L);

        return ResponseEntity.ok(getBookingDtos(bookings));
    }

    private Set<BookingDto> getBookingDtos(List<Booking>bookingList){
        return bookingList.stream().map(booking -> {
            return BookingMapper.toDTO(booking);
        }).collect(Collectors.toSet());
    }

    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDto>> getBookingBySalon(){

        List<Booking> bookings = bookingService.getBookingsBySalon(1L);

        return ResponseEntity.ok(getBookingDtos(bookings));
    }


    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable("bookingId") Long bookingId) throws Exception{

        Booking bookings = bookingService.getBookingById(bookingId);

        return ResponseEntity.ok(BookingMapper.toDTO(bookings));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDto> updateBookingStatus(@PathVariable("bookingId") Long bookingId, @RequestParam BookingStatus status) throws Exception{

        Booking bookings = bookingService.updateBooking(bookingId, status);

        return ResponseEntity.ok(BookingMapper.toDTO(bookings));
    }


    @GetMapping("/slots/salon/{salonId}/date/")
    public ResponseEntity<List<BookingSlotDTO>> getBookingByDate(@PathVariable("salonId") Long salonId, @RequestParam(required = false) LocalDate date) throws Exception{

       List<Booking> bookings = bookingService.getBookingByDates(date,salonId);
        List<BookingSlotDTO> slotsDTO = bookings.stream().map(booking -> {
            BookingSlotDTO slotDTO = new BookingSlotDTO();
            slotDTO.setStartTime(booking.getStartTime());
            slotDTO.setEndTime(booking.getEndTime());
            return slotDTO;
        }).toList();
        return ResponseEntity.ok(slotsDTO);
    }

    @GetMapping("/report")
    public ResponseEntity<SalonRepot> getSalonReport() throws Exception{

        SalonRepot report = bookingService.getSalonReport(1L);

        return ResponseEntity.ok(report);
    }


}
