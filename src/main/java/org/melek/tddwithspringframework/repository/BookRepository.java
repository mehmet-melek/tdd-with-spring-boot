package org.melek.tddwithspringframework.repository;

import org.melek.tddwithspringframework.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
