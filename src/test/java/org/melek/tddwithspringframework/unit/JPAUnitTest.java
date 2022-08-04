package org.melek.tddwithspringframework.unit;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.melek.tddwithspringframework.model.entity.Book;
import org.melek.tddwithspringframework.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith({SpringExtension.class})
@DataJpaTest
public class JPAUnitTest {

    @Autowired
    private TestEntityManager entityManager;


    @Autowired
    BookRepository bookRepository;

    @Test
    void customQueryTest(){
        List<Book> bookList = bookRepository.findByNameStartsWithParam("Test");
        BDDAssertions.then(bookList.size()).isEqualTo(3);
    }

}
