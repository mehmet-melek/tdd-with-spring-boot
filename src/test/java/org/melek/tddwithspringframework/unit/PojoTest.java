package org.melek.tddwithspringframework.unit;

import org.junit.jupiter.api.Test;
import org.melek.tddwithspringframework.model.entity.Book;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;


class PojoTest {

    @Test
    void bookPojoTest(){
        assertPojoMethodsFor(Book.class).testing(Method.GETTER,Method.CONSTRUCTOR)
                .areWellImplemented();
    }

}
