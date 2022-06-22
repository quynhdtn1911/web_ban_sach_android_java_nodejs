package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.controller.ApiService;
import com.example.btl.model.Book;
import com.example.btl.model.BookOrder;
import com.example.btl.model.Data;
import com.example.btl.model.User;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailActivity extends AppCompatActivity {
    private ImageView img, btnInc, btnDec;
    private TextView txtName, txtAuthor, txtGeneres, txtPrice, txtQuantity, txtTotal, txtDesc;
    private Button btnCancel, btnAdd;
    private Book book;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        init();

    }
    private void init(){
        img = findViewById(R.id.img);
        txtName = findViewById(R.id.txtName);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtGeneres = findViewById(R.id.txtGeneres);
        txtPrice = findViewById(R.id.txtPrice);
        txtQuantity = findViewById(R.id.txtQuantity);
        txtTotal = findViewById(R.id.txtTotal);
        txtDesc = findViewById(R.id.txtDesc);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        btnInc = findViewById(R.id.btnInc);
        btnDec = findViewById(R.id.btnDec);
        Intent intent = getIntent();
        book = (Book) intent.getSerializableExtra("book");
        user = (User) intent.getSerializableExtra("user");
        System.out.println(user.getEmail());
        Picasso.get().load("http://192.168.142.1:5000/images/" + book.getImage()).into(img);
        txtName.setText(book.getName());
        txtAuthor.setText(book.getAuthor().getName());
        txtGeneres.setText(book.getGeneres());
        txtPrice.setText(book.getPrice() + "");
        txtQuantity.setText("1");
        txtTotal.setText(book.getPrice() + "");
        txtDesc.setText("");
        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(txtQuantity.getText().toString());
                if(quantity < 10){
                    quantity += 1;
                    txtQuantity.setText(quantity + "");
                    txtTotal.setText(quantity * book.getPrice() + "");
                }
            }
        });
        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(txtQuantity.getText().toString());
                if(quantity > 0){
                    quantity -= 1;
                    txtQuantity.setText(quantity + "");
                    txtTotal.setText(quantity * book.getPrice() + "");
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookDetailActivity.this, HomeActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(txtQuantity.getText().toString());
                BookOrder bookOrders = new BookOrder(user, book, quantity, 0);
                ApiService.apiService.addToCart(bookOrders).enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        Data data = response.body();
                        if(data.isStatus()){
                            Intent intent = new Intent(BookDetailActivity.this, HomeActivity.class);
                            intent.putExtra("user", user);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {

                    }
                });
            }
        });
    }
}