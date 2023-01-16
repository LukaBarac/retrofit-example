package com.retrofit_demo.retrofit_demo.dto;

import lombok.Data;

import java.util.List;
@Data
public class ReportDto {

    private String name;
    private double price;
    private List<CityDto> cities;
    private double totalIncome;
}
