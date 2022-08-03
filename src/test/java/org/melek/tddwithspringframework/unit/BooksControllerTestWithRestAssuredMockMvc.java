package org.melek.tddwithspringframework.unit;

import org.junit.jupiter.api.Test;
import org.melek.tddwithspringframework.controller.BooksController;
import org.melek.tddwithspringframework.service.BookService;
import org.melek.tddwithspringframework.util.BookUtil;
import org.mockito.Mockito;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

public class BooksControllerTestWithRestAssuredMockMvc {

    BookUtil bookUtil = new BookUtil();

    private BookService mockBookService = Mockito.mock(BookService.class);

    @Test
    void sampleControllerTestWithRestAssuredMockMvc() {
        when(mockBookService.getAllBooks()).thenReturn(bookUtil.getSampleBookDtoList());

        given().standaloneSetup(new BooksController(mockBookService))
                .when().get("/books")
                .then()
                .statusCode(200)
                .body("size()",equalTo(3));
    }

}
