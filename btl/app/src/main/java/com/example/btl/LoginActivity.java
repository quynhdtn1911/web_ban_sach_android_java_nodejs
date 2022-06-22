package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.btl.controller.ApiService;
import com.example.btl.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText txtEmail, txtPassword;
    private TextView txtSignup, txtError;
    private Button btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisActivity.class);
                startActivity(intent);
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                if(email.equals("") || password.equals("")){
                    txtError.setVisibility(View.VISIBLE);
                    txtError.setText("Email hoặc mật khẩu không được để trống!");
                }else{
                    User user = new User(email, password);
                    ApiService.apiService.login(user).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User result = response.body();
                            if(result == null){
                                txtError.setVisibility(View.VISIBLE);
                                txtError.setText("Email hoặc mật khẩu không chính xác!");
                            }else{
                                txtError.setVisibility(View.INVISIBLE);
                                txtError.setText("");
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                intent.putExtra("user", result);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
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
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtSignup = findViewById(R.id.txtSignup);
        btnSignin = findViewById(R.id.btnSignin);
        txtError = findViewById(R.id.txtError);
    }
}