package org.melek.tddwithspringframework.util;

import org.springframework.test.context.ActiveProfilesResolver;

public class MyTestProfileResolver implements ActiveProfilesResolver {

    @Override
    public String[] resolve(Class<?> testClass) {
        String envCode = System.getProperty("env.code");
        return new String[]{ envCode == null ? "DEV" : envCode};
    }
}
