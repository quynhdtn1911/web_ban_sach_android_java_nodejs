package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.btl.controller.ApiService;
import com.example.btl.model.Data;
import com.example.btl.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisActivity extends AppCompatActivity {
    private EditText txtName, txtEmail, txtPassword;
    private TextView txtSignin, txtError;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        init();
        txtSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();
                String email = txtEmail.getText().toString();
                String reg = "^[a-zA-Z0-9._]+@[a-zA-Z0-9._]+.[a-zA-Z]$";
                Matcher matcher = Pattern.compile(reg).matcher(email);
                if(!matcher.matches()){
                    txtError.setVisibility(View.VISIBLE);
                    txtError.setText("Email không hợp lệ!");
                }else{
                    String password = txtPassword.getText().toString();
                    User user = new User(name, email, password);
                    ApiService.apiService.regis(user).enqueue(new Callback<Data>() {
                        @Override
                        public void onResponse(Call<Data> call, Response<Data> response) {
                            Data data = response.body();
                            if(data.isStatus() == false){
                                txtError.setVisibility(View.VISIBLE);
                                txtError.setText("Email đã tồn tại trong hệ thống, vui lòng tạo tài khoản với email mới!");
                            }else{
                                txtError.setVisibility(View.INVISIBLE);
                                txtError.setText("");
                                Intent intent = new Intent(RegisActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<Data> call, Throwable t) {

                        }
                    });

                }
            }
        });
        txtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    txtError.setText("");
                    txtError.setVisibility(View.INVISIBLE);
                }
            }
        });
        txtName.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    txtError.setText("");
                    txtError.setVisibility(View.INVISIBLE);
                }
            }
        });
        txtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    txtError.setText("");
                    txtError.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void init(){
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtSignin = findViewById(R.id.txtSignin);
        btnSignup = findViewById(R.id.btnSignup);
        txtError = findViewById(R.id.txtError);
    }
}