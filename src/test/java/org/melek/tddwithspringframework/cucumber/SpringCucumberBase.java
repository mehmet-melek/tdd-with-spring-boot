package org.melek.tddwithspringframework.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.melek.tddwithspringframework.repository.BookRepository;
import org.melek.tddwithspringframework.util.BookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;


import static io.restassured.RestAssured.given;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/sql-files/temp.sql")
public class SpringCucumberBase {

    @Autowired
    BookRepository bookRepository;


    @BeforeClass
    public void setup(){
        bookRepository.save(BookUtil.getSampleBook());
    }

    private final static String BASE_URI = "http://localhost";

    @LocalServerPort
    private int port;

    protected ValidatableResponse validatableResponse;

    private void configureRestAssured() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }

    protected RequestSpecification requestSpecification() {
        configureRestAssured();
        return given();
    }

}

