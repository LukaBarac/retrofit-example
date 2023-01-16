package com.retrofit_demo.retrofit_demo.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.retrofit_demo.retrofit_demo.builder.Blueprint;
import com.retrofit_demo.retrofit_demo.dto.ProductDto;
import com.retrofit_demo.retrofit_demo.dto.ReportDto;
import com.retrofit_demo.retrofit_demo.mapper.ProductMapper;
import com.retrofit_demo.retrofit_demo.model.Product;
import com.retrofit_demo.retrofit_demo.service.ProductService;
import lombok.RequiredArgsConstructor;
//import lombok.var;
import okhttp3.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products/")
public class ProductController /*implements Callback<List<Product>>*/ {

    @Autowired
    private ProductMapper productMapper;

    public String getToken(){
        Call<LoginResponseDto> call = Blueprint.createService(ProductService.class).login("peraperic@gmail.com", "baguvix");
        Response<LoginResponseDto> response;
        String accessToken;
        try {
            response = call.execute();
            accessToken = response.body().getAccessToken();
            return accessToken;
        } catch (Exception e) {
            throw new RuntimeException("error kod logina");
        }
    }

    @GetMapping("")
    public List<ProductDto> getProducts() {
        Call<List<Product>> call = Blueprint.createService(ProductService.class).getProducts();
            Response<List<Product>> response;
        try {
            response = call.execute();
            List<Product> products = response.body();
            List<ProductDto> productsDtos = productMapper.convert(products);
            return productsDtos;
        } catch (Exception e) {
            throw new RuntimeException("doslo je do greske");
        }
    }

    @GetMapping("fetchViaId/{id}")
    public Optional<ProductDto> getProductsById(@PathVariable int id){
        var accessToken = getToken();
        Call<Optional<Product>> productCall = Blueprint.createService(ProductService.class).getProductsById("Bearer " + accessToken, id);
        Response<Optional<Product>> responseProduct;        //vidi sa optional
        try {
            responseProduct = productCall.execute();
            Product product = responseProduct.body().orElseThrow(() -> new Exception("nema")); // moze var stavio samo da potvrdim da je Product
            //return productMapper.map(product);  // vidi kako sa Optional
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("newProducts")
    public List<ProductDto> getNewProducts(){
        var accessToken = getToken();
        Call<List<Product>> call = Blueprint.createService(ProductService.class).findNewProducts("Bearer " + accessToken);
        Response<List<Product>> response;
        try {
            response = call.execute();
            List<Product> products = response.body();
            return productMapper.convert(products);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("topProducts")
    public List<ProductDto> getTopProducts(){
        var accessToken = getToken();
        Call<List<Product>> call = Blueprint.createService(ProductService.class).getTopProducts("Bearer " + accessToken);
        Response<List<Product>> response;
        try {
            response = call.execute();
            List<Product> products = response.body();
            List<ProductDto> productsDtos = productMapper.convert(products); //valja li ovaj convert?
            return productsDtos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("")
    public ProductDto createProduct(@RequestBody Product product) {
        var accessToken = getToken();
        Call<Product> call = Blueprint.createService(ProductService.class).createProduct("Bearer " + accessToken, product);
        Response<Product> response;
        try {
            response = call.execute();
            var createdProduct = response.body();
            return productMapper.map(createdProduct);
        } catch (IOException e) {
            throw new RuntimeException(e + " greska kod kreiranja objekta");
        }
    }

    @PostMapping("addMultipleProducts")
    public List<ProductDto> addProducts(@RequestBody List<Product> products){
        var accessToken = getToken();
        Call<List<Product>> call = Blueprint.createService(ProductService.class).addProducts("Bearer " + accessToken, products);
        Response<List<Product>> response;
        try {
            response = call.execute();
            var createdProducts = response.body();
            return productMapper.convert(createdProducts);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable int id){
        var accessToken = getToken();
        Call<Void> call = Blueprint.createService(ProductService.class).deleteProduct("Bearer " + accessToken, id);
        Response<Void> response;
        try {
            response = call.execute();
            var deleteProduct = response.body();        // vidi da li ovo treba, u sustini samo nas call interesuje, response je nebitan
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("report/{id}")
    public ReportDto getReport(@PathVariable int id){
        var accessToken = getToken();
        Call<ReportDto> call = Blueprint.createService(ProductService.class).getReport("Bearer " + accessToken, id);
        Response<ReportDto> response;
        try {
            response = call.execute();
            return response.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}