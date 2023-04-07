package com.webapp.repository;

import com.webapp.model.BookInventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookInventoryRepository extends PagingAndSortingRepository<BookInventory, Long> {

    @Query(value = "select * from book_inventory " +
            "where title ilike ?1 or publisher ilike ?1 or author ilike ?1 ",nativeQuery = true)
    public Page<BookInventory> findAllByKeyword(String keyword, Pageable paging);
}
