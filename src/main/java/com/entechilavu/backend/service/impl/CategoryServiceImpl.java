package com.entechilavu.backend.service.impl;

import com.entechilavu.backend.dto.CategoryDto;
import com.entechilavu.backend.entity.Category;
import com.entechilavu.backend.repo.CategoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    CategoryRespository categoryRespository;

    @Override
    public Category createCategory(CategoryDto categoryDto) {
        return Optional.ofNullable(categoryDto).map(dto -> {
            Category category = new Category();
            category.setName(categoryDto.getName());
            category.setColor(categoryDto.getColor());
            category.setIcon(categoryDto.getIcon());
            category.setCreatedAt(LocalDate.now());
            return category;
        }).map(categoryRespository::save).orElseThrow(() -> new RuntimeException("Invalid category"));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRespository.findAll()
                .stream().map(category -> {
                    CategoryDto dto = new CategoryDto();
                    dto.setName(category.getName());
                    dto.setColor(category.getColor());
                    dto.setIcon(category.getIcon());
                    return dto;
                }).toList();
    }
}
