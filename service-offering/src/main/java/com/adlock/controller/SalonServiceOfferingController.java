package com.adlock.controller;


import com.adlock.dto.CategoryDto;
import com.adlock.dto.SalonDto;
import com.adlock.dto.ServiceOfferingDto;
import com.adlock.model.ServiceOffering;
import com.adlock.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-offering/salon-owner")
@RequiredArgsConstructor
public class SalonServiceOfferingController {

    private final ServiceOfferingService service;

    @PostMapping("")
    public ResponseEntity<ServiceOffering> createService(@RequestBody ServiceOfferingDto  offer){
        SalonDto salonDto = new SalonDto();
        salonDto.setId(1L);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(offer.getCategoryId());
ServiceOffering ser = service.createService(salonDto,offer,categoryDto);
  return new ResponseEntity<>(ser, HttpStatus.CREATED);
    }

@PostMapping("/{serviceId}")
    public ResponseEntity<ServiceOffering> updateService(@PathVariable("serviceId") Long serviceId, @RequestBody ServiceOffering serviceOffering) throws Exception{

        ServiceOffering ser = service.updateService(serviceId, serviceOffering);
        return new ResponseEntity<>(ser, HttpStatus.CREATED);
    }

}
