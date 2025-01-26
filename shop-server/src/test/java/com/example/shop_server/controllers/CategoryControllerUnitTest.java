package com.example.shop_server.controllers;

import com.example.shop_server.controllers.CategoryController;
import com.example.shop_server.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CategoryControllerUnitTest {

    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    public void testCreateCategory() throws Exception {
        
        Category category = new Category();
        category.setName("Sports");

        when(categoryService.createCategory(Mockito.any(Category.class))).thenReturn(category);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();

        mockMvc.perform(post("/api/v1/categories")
                .contentType("application/json")
                .content("{\"name\":\"Sports\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Sports"));
        
    }

    @Test
    public void testGetCategoryById() throws Exception {
        Category category = new Category();
        category.setName("Sports");

        when(categoryService.getCategoryById(1L)).thenReturn(category);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();

        mockMvc.perform(get("/api/v1/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sports"));
        
    }

    @Test
    public void testUpdateCategory() throws Exception {
        
        Category category = new Category();
        category.setName("Sports");

        when(categoryService.updateCategory(1L, category)).thenReturn(category);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();

        mockMvc.perform(put("/api/v1/categories/1")
                .contentType("application/json")
                .content("{\"name\":\"Updated Sports\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Sports"));
        
    }

    @Test
    public void testDeleteCategory() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();

        mockMvc.perform(delete("/api/v1/categories/1"))
                .andExpect(status().isNoContent());
        
    }
}
