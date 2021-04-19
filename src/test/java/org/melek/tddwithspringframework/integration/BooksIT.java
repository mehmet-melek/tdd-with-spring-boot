package org.melek.tddwithspringframework.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.melek.tddwithspringframework.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BooksIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void getBooks() {
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
        assertThat(books.size()).isGreaterThan(1);
    }


    @Test
    public void getBookWithId() {
        //Arrange
        //Act
        ResponseEntity<Book> responseEntity = testRestTemplate.getForEntity("/books/1", Book.class);

        //Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getId()).isEqualTo(1);
    }

    @Test
    public void getBookWithNonExistId() {
        //Arrange
        //Act
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/books/0", String.class);

        //Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertTrue(responseEntity.getBody().contains("Book not found!"));
    }


}
