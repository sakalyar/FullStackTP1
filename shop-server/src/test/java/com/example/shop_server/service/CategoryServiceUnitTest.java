package com.example.shop_server.service;


import com.example.shop_server.model.Category;
import com.example.shop_server.repository.CategoryRepository;
import com.example.shop_server.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoryServiceUnitTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void testCreateCategory() {
        
        Category category = new Category();
        category.setName("Sports");

        when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);

        Category result = categoryService.createCategory(category);

        assertThat(result.getName()).isEqualTo("Sports");
        
    }

    @Test
    public void testGetCategoryById() {
        
        Category category = new Category();
        category.setName("Sports");

        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.of(category));

        Category result = categoryService.getCategoryById(1L);

        assertThat(result.getName()).isEqualTo("Sports");
        
    }

    @Test
    public void testGetAllCategories() {
        
        Category category1 = new Category();
        category1.setName("Sports");

        Category category2 = new Category();
        category2.setName("Electronics");

        when(categoryRepository.findAll()).thenReturn(java.util.List.of(category1, category2));

        java.util.List<Category> result = categoryService.getAllCategories();

        assertThat(result.size()).isGreaterThan(1);
        
    }

    @Test
    public void testUpdateCategory() {
        
        Category category = new Category();
        category.setName("Sports");

        when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);

        Category result = categoryService.updateCategory(1L, category);

        assertThat(result.getName()).isEqualTo("Sports");
        
    }

    @Test
    public void testDeleteCategory() {
        
        Category category = new Category();
        category.setName("Sports");

        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.of(category));

        categoryService.deleteCategory(1L);

        Mockito.verify(categoryRepository, Mockito.times(1)).delete(category);
        
    }
}