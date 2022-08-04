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
    private TestEntityManager testEntityManager;

    @Autowired
    BookRepository bookRepository;

    //First data coming from data.sql file and second data created with testEntityManager.persist.
    //So expected result is 2
    @Test
    void customQueryTest(){

        //Arrange
        Book book = Book.builder()
                .name("Dummy book name Test")
                .author("Dummy author")
                .price(20)
                .stock(3).build();
        testEntityManager.persist(book);

        //Act
        List<Book> bookList = bookRepository.findByNameStartsWithParam("Test");

        //Assert
        BDDAssertions.then(bookList.size()).isEqualTo(2);
    }

}
