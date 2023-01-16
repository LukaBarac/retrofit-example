package com.retrofit_demo.retrofit_demo.service;

import com.retrofit_demo.retrofit_demo.controller.LoginResponseDto;
import com.retrofit_demo.retrofit_demo.dto.ProductDto;
import com.retrofit_demo.retrofit_demo.dto.ReportDto;
import com.retrofit_demo.retrofit_demo.model.Product;
import org.springframework.web.bind.annotation.RequestParam;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    @GET("products/")                  // ovo gadja api druge app
    Call<List<Product>> getProducts();

    @GET("products/topProducts")
    Call<List<Product>> getTopProducts(@Header("Authorization") String credentials);

    @GET("products/{id}")
    Call<Optional<Product>> getProductsById(@Header("Authorization") String credentials, @Path("id") int id);

    @POST("products/")
    Call<Product> createProduct(@Header("Authorization") String credentials, @Body Product product);

    @GET("products/newProducts")
    Call<List<Product>> findNewProducts(@Header("Authorization") String credentials);

    @DELETE("products/{id}")
    Call<Void> deleteProduct(@Header("Authorization") String credentials, @Path("id") int id);

    @POST("products/addMultipleProducts")
    Call<List<Product>> addProducts(@Header("Authorization") String credentials, @Body List<Product> products);

    @POST("login")
    Call<LoginResponseDto> login(@Query("username") String username, @Query("password") String password);

    @GET("products/report/{id}")
    Call<ReportDto> getReport(@Header("Authorization") String credentials, @Path("id") int id);

}
