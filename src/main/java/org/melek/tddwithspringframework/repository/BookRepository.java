package org.melek.tddwithspringframework.repository;

import org.melek.tddwithspringframework.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);

    @Query("SELECT c FROM Book c WHERE c.name LIKE CONCAT('%',:ending, '%')")
    List<Book> findByNameStartsWithParam(@Param("ending") String ending);
}
