package com.entechilavu.backend.service.impl;

import com.entechilavu.backend.dto.CategoryDto;
import com.entechilavu.backend.entity.Category;

public interface CategoryService {

    Category createCategory(CategoryDto categoryDto);
}
