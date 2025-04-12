package com.adlock.controller;

import com.adlock.model.ServiceOffering;
import com.adlock.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/service-offering")
@RequiredArgsConstructor
public class ServiceOfferingController {

    private final ServiceOfferingService service;

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getServiceBySalonId(@PathVariable("salonId") Long salonId, @RequestParam(required = false) Long categoryId){
       Set<ServiceOffering> serv =  service.getAllServiceBySalonId(salonId, categoryId);
      return new ResponseEntity<>(serv, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering> getServiceById(@PathVariable("id") Long id)throws Exception{
        ServiceOffering serv = service.getServiceById(id);
        return new ResponseEntity<>(serv, HttpStatus.OK);
    }
 @GetMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServicesByIds(Set<Long> ids){
        Set<ServiceOffering> serv = service.getServiceByIds(ids);
        return new ResponseEntity<>(serv, HttpStatus.OK);
    }

}
