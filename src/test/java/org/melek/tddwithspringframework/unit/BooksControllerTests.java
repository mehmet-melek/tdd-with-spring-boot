package org.melek.tddwithspringframework.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.melek.tddwithspringframework.controller.BooksController;
import org.melek.tddwithspringframework.service.BookService;
import org.melek.tddwithspringframework.util.BookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BooksController.class)
public class BooksControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void getAllBooks () throws Exception {
        //Arrange
        when(bookService.getAllBooks()).thenReturn(BookUtil.getSampleBookList());
        //Act
        //Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

}
