package com.retrofit_demo.retrofit_demo.model;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
public class Product {

//    private int id;
    private String name;
    private double price;
    private int stockAmount;
}
