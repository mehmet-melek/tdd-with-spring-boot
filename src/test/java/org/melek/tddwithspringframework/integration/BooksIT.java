package org.melek.tddwithspringframework.integration;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.melek.tddwithspringframework.model.Book;
import org.melek.tddwithspringframework.repository.BookRepository;
import org.melek.tddwithspringframework.util.BookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //used for non-static before method
@Transactional
//@ActiveProfiles("test")
class BooksIT {

    @Autowired
    private BookRepository bookRepository;

    @BeforeAll
    void setup() {
        bookRepository.saveAll(BookUtil.getSampleBookList());
    }

    @Autowired
    TestRestTemplate testRestTemplate;

    @Nested
    @DisplayName("First Group")
    class first {

        @Test
        @Sql(scripts = "classpath:/data.sql")
        void whenGetBooks_shouldReturnAllBooks() {
            //Arrange
            //Act
            ResponseEntity<List<Book>> responseEntity = testRestTemplate.exchange(
                    "/books",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Book>>() {
                    });
            List<Book> books = responseEntity.getBody();

            //Assert
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(books.size()).isEqualTo(5);
        }


        @Test
        void whenGetBookWithId_shouldReturnGivenBook() throws InterruptedException {
            //Arrange
            //Act
            ResponseEntity<Book> responseEntity = testRestTemplate.getForEntity("/books/1", Book.class);

            //Assert
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(responseEntity.getBody().getId()).isEqualTo(1);
        }

    }

    @Nested
    @DisplayName("Second Group")
    class second {
        @Test
        void whenGetBookWithNonExistId_shouldReturnNotFoundMessage() {
            //Arrange
            //Act
            ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/books/0", String.class);

            //Assert
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
            assertTrue(responseEntity.getBody().contains("Book not found!"));
        }

        @Test
        void whenSaveBook_shouldReturnSavedBook() {
            //Arrange
            //Act
            ResponseEntity<Book> responseEntity = testRestTemplate.postForEntity("/books", BookUtil.getSampleBook(), Book.class);
            //Assert
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            assertThat(responseEntity.getBody().getId()).isEqualTo(1);
            assertThat(responseEntity.getBody().getName()).isEqualTo(BookUtil.getSampleBook().getName());

        }
    }


}
