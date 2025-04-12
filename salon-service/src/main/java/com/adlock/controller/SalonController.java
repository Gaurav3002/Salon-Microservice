package com.adlock.controller;

import com.adlock.mapper.SalonMapper;
import com.adlock.model.Salon;
import com.adlock.payload.dto.SalonDTO;
import com.adlock.payload.dto.UserDTO;
import com.adlock.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salons")
@RequiredArgsConstructor
public class SalonController {

    private final SalonService salonService;

    @PostMapping
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO req){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.createSalon(req, userDTO);
        SalonDTO salonDTO = SalonMapper.mapToDto(salon);
        return new ResponseEntity<>(salonDTO, HttpStatus.CREATED);
    }

 @PatchMapping("/{salonId}")
    public ResponseEntity<SalonDTO> updateSalon(@PathVariable("salonId") Long salonId, @RequestBody SalonDTO salonDTO) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        Salon salon = salonService.updateSalon(salonDTO, userDTO, salonId);
        SalonDTO salonDT = SalonMapper.mapToDto(salon);
        return new ResponseEntity<>(salonDT, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<SalonDTO>> getAllSalon(){

     List<Salon> salons =    salonService.getAllSalons();
     // what data we  transfer we transfer into the dto formate
     List<SalonDTO> salonDTOS = salons.stream().map((salon) -> {
         SalonDTO salonDTO = SalonMapper.mapToDto(salon);
         return salonDTO;
     }) .toList();

     return new ResponseEntity<>(salonDTOS, HttpStatus.OK);
    }

  @GetMapping("/{salonId}")
    public ResponseEntity<SalonDTO> getSalonById(@PathVariable("salonId") Long salonId) throws Exception {

        Salon salon =    salonService.getSalonById(salonId);
            SalonDTO salonDTO = SalonMapper.mapToDto(salon);


        return new ResponseEntity<>(salonDTO, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SalonDTO>> searchSalons(@RequestParam("city") String city) throws Exception {

        List<Salon> salons =    salonService.searchSalonByCity(city);
        List<SalonDTO> salonDTOS = salons.stream().map((salon) ->{
            SalonDTO salonDTO = SalonMapper.mapToDto(salon);
            return salonDTO;
        }).toList();

        return new ResponseEntity<>(salonDTOS, HttpStatus.OK);
    }


    @GetMapping("/owner")
    public ResponseEntity<SalonDTO> getSalonByOwnerId(@RequestParam("id") Long id) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon =    salonService.getSalonById(id);
        SalonDTO salonDTO = SalonMapper.mapToDto(salon);


        return new ResponseEntity<>(salonDTO, HttpStatus.OK);
    }



}
