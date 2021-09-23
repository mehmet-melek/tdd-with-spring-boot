package org.melek.tddwithspringframework.integration;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.melek.tddwithspringframework.util.MyTestProfileResolver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //used for non-static before method
@ActiveProfiles(resolver = MyTestProfileResolver.class) //required application-test.properties file
public class BooksRestAssuredIT {
    private final static String BASE_URI = "http://localhost";

    @LocalServerPort
    private int port;

    private Response response;

    @BeforeAll
    void configureRestAssured() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }

    @Test
    void whenGetBooks_shouldReturnAllBooks() {
        response = when().get("/books");
        response.then().assertThat()
                .statusCode(equalTo(200))
                .body("size()",is(2))
                .body("name[0]",equalTo("Clean Code"))
                .body("name[1]",equalTo("Continuous Delivery"));
    }

    @Test
    void whenGet_booksWithId_shouldReturnGivenBook() throws Exception {
        response = when().get("/books/1");
        response.then().assertThat()
                .statusCode(equalTo(200))
                .body("id", is(1))
                .body("name", equalTo("Clean Code"));
    }




}
