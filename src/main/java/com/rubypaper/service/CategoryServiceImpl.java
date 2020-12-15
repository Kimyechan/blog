package com.rubypaper.service;

import com.rubypaper.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public void searchCategory() {

    }

    @Override
    public void createCategory() {

    }

    @Override
    public void updateCategory() {

    }

    @Override
    public void deleteCategory() {

    }
}
