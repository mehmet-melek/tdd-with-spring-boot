package org.melek.tddwithspringframework.unit;

import org.junit.jupiter.api.Test;
import org.melek.tddwithspringframework.model.Book;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;


public class PojoTest {

    @Test
    void bookPojoTest(){

        final Class<?> classUnderTest = Book.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER)
                .testing(Method.CONSTRUCTOR)
                .areWellImplemented();
    }

}
