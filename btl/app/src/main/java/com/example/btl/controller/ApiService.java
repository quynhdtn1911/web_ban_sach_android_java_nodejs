package com.example.btl.controller;

import com.example.btl.model.Book;
import com.example.btl.model.BookOrder;
import com.example.btl.model.Data;
import com.example.btl.model.Order;
import com.example.btl.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("dd/MM/yyyy HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.142.1:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @POST("auth/login")
    Call<User> login(@Body User user);

    @POST("auth/regis")
    Call<Data> regis(@Body User user);

    @GET("book")
    Call<ArrayList<Book>> getBooks();

    @GET("book/{generes}")
    Call<ArrayList<Book>> getBooksByGeneres(@Path("generes") String generes);

    @POST("cart/add")
    Call<Data> addToCart(@Body BookOrder bookOrder);

    @GET("cart/{idUser}")
    Call<ArrayList<BookOrder>> getCart(@Path("idUser") String idUser);

    @POST("cart/delete")
    Call<Data> removeFromCart(@Body ArrayList<String> listChecked);

    @POST("order/add")
    Call<Data> order(@Body Order order);

    @GET("order/{idUser}")
    Call<ArrayList<Order>> getOrder(@Path("idUser") String idUser);

    @POST("auth/changePassword")
    Call<Data> changePassword(@Body User user);

    @GET("/book/search")
    Call<ArrayList<Book>> searchBook(@Query("name") String name);
}
