package com.example.shop_server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.shop_server.model.Category;
import com.example.shop_server.repository.CategoryRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CategoryRepository repository;
    @Override
    public void run(String... args) {
        if (repository.count() == 0) { // Check if the database is empty
            Category rootCategory = new Category();
            rootCategory.setName("Root Category");
            repository.save(rootCategory);
    
            Category childCategory = new Category();
            childCategory.setName("Child Category");
            childCategory.setParent(rootCategory);
            repository.save(childCategory);
        }
    }
    
}
