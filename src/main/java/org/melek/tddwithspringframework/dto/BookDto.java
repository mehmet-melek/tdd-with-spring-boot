package org.melek.tddwithspringframework.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class BookDto {
    private String name;
    private String author;
    private double price;
    private int stock;
}
