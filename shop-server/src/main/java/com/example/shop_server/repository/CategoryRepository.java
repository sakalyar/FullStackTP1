package com.example.shop_server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.shop_server.model.Category;

import java.util.Date;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // E_CAT_50: Paginated search with basic details
    Page<Category> findAll(Pageable pageable);

    // E_CAT_60: Filtering by root status
    Page<Category> findByParentIsNull(Pageable pageable); // Root categories
    Page<Category> findByParentIsNotNull(Pageable pageable); // Child categories

    // E_CAT_60: Filtering by date ranges
    Page<Category> findByCreatedAtAfter(Date afterDate, Pageable pageable);
    Page<Category> findByCreatedAtBefore(Date beforeDate, Pageable pageable);
    Page<Category> findByCreatedAtBetween(Date startDate, Date endDate, Pageable pageable);

    // E_CAT_70: Sorting by child count
    @Query("SELECT c FROM Category c LEFT JOIN c.children ch GROUP BY c ORDER BY COUNT(ch) DESC")
    Page<Category> findAllSortedByChildCount(Pageable pageable);
}
