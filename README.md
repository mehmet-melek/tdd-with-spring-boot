# Tdd with Spring Framework
An example of test driven development with the spring framework.

**Todos**
1. Define acceptance criteria
2. Write failing acceptance test
3. Write failing unit test
4. Make the test pass
5. Refactor


---
### Define acceptance criteria for a simple bookstore application.

>When I get the _books_  
>Then it should return all books  
>And status code should be _200_  

>When ı get _books/{id}_  
>Then it should return given book  
>And status code should be _200_  

>When ı get _books/{id}_ with non-existent id  
>Then it should return "Book not found" error message  
>And status code should be _404_  

>When I want to add a book to _books_.  
>Then should return given book  
>And status code should be _201_  
