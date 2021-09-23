package org.melek.tddwithspringframework.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.melek.tddwithspringframework.controller.BooksController;
import org.melek.tddwithspringframework.exception.BookNotFoundException;
import org.melek.tddwithspringframework.service.BookService;
import org.melek.tddwithspringframework.util.BookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BooksController.class)
class BooksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void whenGet_books_shouldReturnAllBooks() throws Exception {
        //Arrange
        when(bookService.getAllBooks()).thenReturn(BookUtil.getSampleBookList());
        //Act
        //Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("size()").value(3))
                .andReturn();

        verify(bookService, times(1)).getAllBooks();

    }

    @Test
    void whenGet_booksWithId_shouldReturnGivenBook() throws Exception {
        //Arrange
        when(bookService.getBookWithId(anyLong())).thenReturn(BookUtil.getSampleBook());
        //Act
        //Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/books/{bookId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("name").value("Clean Code"));
        verify(bookService, times(1)).getBookWithId(1L);
    }

    @Test
    void whenGet_BooksWithNonExistId_shouldReturnNotFoundMessage() throws Exception {
        //Arrange
        when(bookService.getBookWithId(anyLong())).thenThrow(new BookNotFoundException());
        //Act
        //Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/books/{bookId}", 1))
                .andExpect(status().isNotFound())
                .andExpect(result -> result.getResponse().toString().contains("Book not found!"));
        // .andExpect(status().reason(containsString("Book not found!")))
        // .andExpect( status().reason( "Book not found!" ));
        verify(bookService, times(1)).getBookWithId(1L);

    }

    @Test
    void whenPostBookTo_books_shouldReturnSavedBookWithId() throws Exception {
        //Arrange
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(BookUtil.getSampleBook());
        when(bookService.addBook(any())).thenReturn(BookUtil.getSampleBook());
        //Act
        //Assert
        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value(BookUtil.getSampleBook().getName()));
        verify(bookService, times(1)).addBook(any());
    }
}
