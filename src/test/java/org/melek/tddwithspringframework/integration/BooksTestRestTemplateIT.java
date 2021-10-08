package org.melek.tddwithspringframework.integration;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.melek.tddwithspringframework.dto.BookDto;
import org.melek.tddwithspringframework.model.entity.Book;
import org.melek.tddwithspringframework.repository.BookRepository;
import org.melek.tddwithspringframework.util.BookUtil;
import org.melek.tddwithspringframework.util.MyTestProfileResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles(resolver = MyTestProfileResolver.class) //required application-test.properties file
@Sql(scripts = "/sql-files/test-data.sql" ,executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql-files/clear-test-data.sql" ,executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class BooksTestRestTemplateIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Nested
    @DisplayName("Get operations")
    class get {

        @Test
        void whenGetBooks_shouldReturnAllBooks() {
            //Arrange
            //Act
            ResponseEntity<List<BookDto>> responseEntity = testRestTemplate.exchange(
                    "/books",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<BookDto>>() {
                    });
            List<BookDto> books = responseEntity.getBody();
            System.out.println(responseEntity.getBody());
            //Assert
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(books.size()).isEqualTo(2);
        }


        @Test
        void whenGetBookWithId_shouldReturnGivenBook() throws InterruptedException {
            //Arrange
            //Act
            ResponseEntity<BookDto> responseEntity = testRestTemplate.getForEntity("/books/1", BookDto.class);
            //Assert
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        @Test
        void whenGetBookWithNonExistId_shouldReturnNotFoundMessage() {
            //Arrange
            //Act
            ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/books/0", String.class);
            //Assert
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
            assertTrue(responseEntity.getBody().contains("Book not found!"));
        }
    }

    @Nested
    @DisplayName("Post operations")
    class post {
        @Test
        void whenSaveBook_shouldReturnSavedBook() {
            //Arrange
            //Act
            ResponseEntity<BookDto> responseEntity = testRestTemplate.postForEntity("/books", BookUtil.getSampleBookDto(), BookDto.class);
            //Assert
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            assertThat(responseEntity.getBody().getName()).isEqualTo(BookUtil.getSampleBook().getName());
        }
    }
}
