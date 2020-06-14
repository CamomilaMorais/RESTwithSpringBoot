package com.camomila.repository;

import com.camomila.data.model.Book;
import com.camomila.data.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Book> findBookByTitle(@Param("title") String title, Pageable pageable);
}
