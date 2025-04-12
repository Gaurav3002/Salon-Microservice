package com.adlock.controller;

import com.adlock.dto.SalonDTO;
import com.adlock.model.Category;
import com.adlock.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories/salon-owner")
@RequiredArgsConstructor
public class SalonCategoryController {

    private final CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        Category saveCategory = categoryService.saveCategory(category, salonDTO);
        return new ResponseEntity<>(saveCategory, HttpStatus.CREATED);
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) throws Exception {
       SalonDTO salonDTO = new SalonDTO();
       salonDTO.setId(1L);
       categoryService.deleteCategoryById(id, salonDTO.getId());
        return new ResponseEntity<>("category deleted Sucessfully", HttpStatus.ACCEPTED);
    }



}
