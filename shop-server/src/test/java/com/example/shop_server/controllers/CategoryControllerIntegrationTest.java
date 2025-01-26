package com.example.shop_server.controllers;


import com.example.shop_server.model.Category;
import com.example.shop_server.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateCategory() {
        
        Category category = new Category();
        category.setName("Sports");

        ResponseEntity<Category> response = restTemplate.postForEntity("/api/v1/categories", category, Category.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody().getName()).isEqualTo("Sports");
        
    }

    @Test
    public void testGetCategoryById() {
        
        Category category = new Category();
        category.setName("Sports");
        categoryRepository.save(category);

        ResponseEntity<Category> response = restTemplate.getForEntity("/api/v1/categories/" + category.getId(), Category.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getName()).isEqualTo("Sports");
        
    }

    @Test
    public void testGetAllCategories() {
        
        Category category1 = new Category();
        category1.setName("Sports");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setName("Electronics");
        categoryRepository.save(category2);

        ResponseEntity<Category[]> response = restTemplate.getForEntity("/api/v1/categories", Category[].class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().length).isGreaterThan(1);
        
    }

    @Test
    public void testUpdateCategory() {
        
        Category category = new Category();
        category.setName("Sports");
        categoryRepository.save(category);

        category.setName("Updated Sports");

        HttpEntity<Category> request = new HttpEntity<>(category);
        ResponseEntity<Category> response = restTemplate.exchange("/api/v1/categories/" + category.getId(), HttpMethod.PUT, request, Category.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getName()).isEqualTo("Updated Sports");
        
    }

    @Test
    public void testDeleteCategory() {
        
        Category category = new Category();
        category.setName("Sports");
        categoryRepository.save(category);

        restTemplate.delete("/api/v1/categories/" + category.getId());

        ResponseEntity<Category> response = restTemplate.getForEntity("/api/v1/categories/" + category.getId(), Category.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
        
    }
}