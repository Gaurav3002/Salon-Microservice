package com.adlock.service;

import com.adlock.dto.CategoryDto;
import com.adlock.dto.SalonDto;
import com.adlock.dto.ServiceOfferingDto;
import com.adlock.model.ServiceOffering;

import java.util.List;
import java.util.Set;

public interface ServiceOfferingService {
    ServiceOffering createService(SalonDto salonDto, ServiceOfferingDto serviceOfferingDto, CategoryDto categoryDto);
   ServiceOffering updateService(Long serviceId, ServiceOffering serviceOffering) throws Exception;
   Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long categoryId);
   Set<ServiceOffering> getServiceByIds(Set<Long> ids);
   ServiceOffering getServiceById(Long id) throws Exception;

}
