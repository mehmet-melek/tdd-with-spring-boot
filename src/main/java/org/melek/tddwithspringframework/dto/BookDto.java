package org.melek.tddwithspringframework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookDto {

    private String name;
    private String author;
    private double price;
    private int stock;
}
