package org.melek.tddwithspringframework.util;


import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@AutoConfigureMessageVerifier
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTestClass {


    @Autowired
    MockMvc mockMvc;


// use for standalone setup

/*
    @Autowired
    private BooksController booksController;

    @BeforeAll
    public void setup() {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder
                = MockMvcBuilders.standaloneSetup(booksController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }
*/
}