package org.melek.tddwithspringframework.unit;

import org.junit.jupiter.api.Test;
import org.melek.tddwithspringframework.model.Book;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;


public class PojoTest {

    @Test
    public void bookPojoTest(){
        assertPojoMethodsFor(Book.class).testing(Method.GETTER, Method.SETTER,Method.CONSTRUCTOR)
                .areWellImplemented();
    }

}
