package com.adlock.controller;


import com.adlock.model.Category;
import com.adlock.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;

    @GetMapping("/salon/{id}")
    public ResponseEntity<Set<Category>> getCategroyBySalon(@PathVariable("id") Long id){
        Set<Category> categories = categoryService.getAllCategoryBySalon(id);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategroyById(@PathVariable("id") Long id) throws Exception {
        Category categories = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
