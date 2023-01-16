package com.retrofit_demo.retrofit_demo;

import com.retrofit_demo.retrofit_demo.controller.ProductController;
import com.retrofit_demo.retrofit_demo.model.Product;
import com.retrofit_demo.retrofit_demo.service.ProductService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import static com.retrofit_demo.retrofit_demo.builder.ServiceGenerator.createService;

@SpringBootApplication
public class RetrofitDemoApplication {

/*	@Autowired
	ProductService productService;*/

	public static void main(String[] args) {
		SpringApplication.run(RetrofitDemoApplication.class, args);
		/*ProductController controller = new ProductController();
		controller.start();*/
/*
		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://localhost:8080")
				.addConverterFactory(GsonConverterFactory.create())
				.client(httpClient.build())
				.build();*/

	/*	ProductService productService = createService(ProductService.class);
		Call<Product> callSync = productService.getProducts();


		Response<Product> response;

		{
			try {
				response = callSync.execute();
				Product product = response.body();
			} catch (Exception e) {
				throw new RuntimeException("doslo je do greske");
			}
		}*/
	}


}
