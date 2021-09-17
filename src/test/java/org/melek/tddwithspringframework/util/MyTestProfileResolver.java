package org.melek.tddwithspringframework.util;

import org.springframework.test.context.ActiveProfilesResolver;
public class MyTestProfileResolver implements ActiveProfilesResolver {
    @Override
    public String[] resolve(Class<?> testClass) {
        return new String[]{System.getProperty("env.code")};
    }
}
