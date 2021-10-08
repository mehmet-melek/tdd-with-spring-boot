package org.melek.tddwithspringframework.integration;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.melek.tddwithspringframework.util.MyTestProfileResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles(resolver = MyTestProfileResolver.class) //required application-test.properties file
@AutoConfigureWebTestClient
@Sql(scripts = "/sql-files/test-data.sql" ,executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql-files/clear-test-data.sql" ,executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//todo : pass parameter to TestRestTemplate
public class BooksWebTestClientIT {

    //Not: Testlerin çalışması için "spring-boot-starter-webflux" gerekli !!!

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenGetBooks_shouldReturnAllBooks() {
        this.webTestClient.get()
                .uri("/books")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$[0].name").isEqualTo("Clean Code")
                .jsonPath("$[1].name").isEqualTo("Continuous Delivery")
                .consumeWith(System.out::println);
    }

    @Test
    void whenGetBooksWitId_shouldReturnGivenBook() {
        this.webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/books/name").queryParam("bookName","Clean Code").build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(4)
                .jsonPath("name").isEqualTo("Clean Code")
                .jsonPath("author").isEqualTo("a")
                .consumeWith(System.out::println);
    }






}
