package com.example.shop_server.service;

import com.example.shop_server.model.Category;
import com.example.shop_server.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    // E_CAT_10: Create a new category
    public Category createCategory(Category category) {
        if (category.getParent() != null && category.getParent().getId().equals(category.getId())) {
            throw new IllegalArgumentException("A category cannot be its own parent (E_CAT_90).");
        }
        return repository.save(category);
    }

    // E_CAT_20 & E_CAT_30: Update existing categories
    public Category updateCategory(Category category) {
        Optional<Category> existing = repository.findById(category.getId());
        if (existing.isEmpty()) {
            throw new IllegalArgumentException("Category does not exist.");
        }
        if (category.getParent() != null && category.getParent().getId().equals(category.getId())) {
            throw new IllegalArgumentException("A category cannot be its own parent (E_CAT_90).");
        }
        return repository.save(category);
    }

    // E_CAT_40: Delete a category
    public void deleteCategoryById(Long id) {
        repository.deleteById(id);
    }

    // E_CAT_50: Paginated retrieval
    public Page<Category> getCategoryList(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // E_CAT_60: Filter by root/child and date ranges
    public Page<Category> getCategoriesByFilter(Boolean isRoot, Date afterDate, Date beforeDate, Pageable pageable) {
        if (isRoot != null) {
            return isRoot
                    ? repository.findByParentIsNull(pageable)
                    : repository.findByParentIsNotNull(pageable);
        } else if (afterDate != null && beforeDate != null) {
            return repository.findByCreatedAtBetween(afterDate, beforeDate, pageable);
        } else if (afterDate != null) {
            return repository.findByCreatedAtAfter(afterDate, pageable);
        } else if (beforeDate != null) {
            return repository.findByCreatedAtBefore(beforeDate, pageable);
        }
        return repository.findAll(pageable);
    }

    // E_CAT_70: Sort by child count
    public Page<Category> getCategoriesSortedByChildCount(Pageable pageable) {
        return repository.findAllSortedByChildCount(pageable);
    }

    // E_CAT_80: Get details of a specific category
    public Category getCategoryById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found."));
    }

    public Page<Category> getCategoriesByFilterAndSort(Boolean isRoot, Date afterDate, Date beforeDate, String sortBy, String sortDirection, Pageable pageable) {
    Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
            sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());

    if (isRoot != null) {
        return isRoot ? repository.findByParentIsNull(sortedPageable) : repository.findByParentIsNotNull(sortedPageable);
    } else if (afterDate != null && beforeDate != null) {
        return repository.findByCreatedAtBetween(afterDate, beforeDate, sortedPageable);
    } else if (afterDate != null) {
        return repository.findByCreatedAtAfter(afterDate, sortedPageable);
    } else if (beforeDate != null) {
        return repository.findByCreatedAtBefore(beforeDate, sortedPageable);
    }
    return repository.findAll(sortedPageable);
}

}
