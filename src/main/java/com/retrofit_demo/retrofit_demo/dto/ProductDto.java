package com.retrofit_demo.retrofit_demo.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private double price;
    @SerializedName("stockAmount")
    private int stockAmount;
}
