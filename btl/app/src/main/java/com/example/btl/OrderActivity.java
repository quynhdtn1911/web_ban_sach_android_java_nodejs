package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.controller.ApiService;
import com.example.btl.model.BookOrder;
import com.example.btl.model.Data;
import com.example.btl.model.Order;
import com.example.btl.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    private EditText txtAddress;
    private Spinner spShipment;
    private TextView txtShipment, txtTotal;
    private Button btnCancel, btnSave;
    private User user;
    private ArrayList<String> listSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        listSelected = (ArrayList<String>) intent.getSerializableExtra("listSelected");
        init();

    }
    private void init(){
        txtAddress = findViewById(R.id.txtAddress);
        spShipment = findViewById(R.id.spShipment);
        txtShipment = findViewById(R.id.txtShipment);
        txtTotal = findViewById(R.id.txtTotal);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
        btnCancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, HomeActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = txtAddress.getText().toString();
                int shipment = spShipment.getSelectedItemPosition();
                ArrayList<BookOrder> bookOrders = new ArrayList<BookOrder>();
                for(int i=0; i<listSelected.size(); ++i){
                    BookOrder bookOrder = new BookOrder();
                    bookOrder.setId(listSelected.get(i));
                    bookOrders.add(bookOrder);
                }
                Order order = new Order(user, bookOrders, address, 0, shipment);
                ApiService.apiService.order(order).enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        Data data = response.body();
                        if(data.isStatus()){
                            Intent intent = new Intent(OrderActivity.this, HomeActivity.class);
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
        spShipment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    txtShipment.setText("30000");
                }else{
                    txtShipment.setText("20000");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}