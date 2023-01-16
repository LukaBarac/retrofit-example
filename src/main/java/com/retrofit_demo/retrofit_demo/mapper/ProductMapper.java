package com.retrofit_demo.retrofit_demo.mapper;

import com.retrofit_demo.retrofit_demo.dto.ProductDto;
import com.retrofit_demo.retrofit_demo.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public Product map(ProductDto productDto){      // pravim konstruktor producta zato mi ne treba product kao entry
        return new Product(productDto.getName(),
                            productDto.getPrice(),
                            productDto.getStockAmount());
    }

    public ProductDto map(Product product){
        return new ProductDto(product.getName(),
                                product.getPrice(),
                                product.getStockAmount());
    }

    public List<ProductDto> convert(List<Product> products){
       return products.stream().map(p -> map(p)).collect(Collectors.toList());
    }                                   //new ProductDto(p.getName(), p.getPrice(), p.getStockAmount())
}                                       // da stoji map ili da stavim this lambdu?

