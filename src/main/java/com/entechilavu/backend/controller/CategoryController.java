package com.entechilavu.backend.controller;


import com.entechilavu.backend.dto.CategoryDto;
import com.entechilavu.backend.dto.ResponseDto;
import com.entechilavu.backend.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {


    @Autowired
    CategoryService categoryService;


    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto("200", "Successfully created category"));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }


}
