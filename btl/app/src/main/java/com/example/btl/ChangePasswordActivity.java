package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.controller.ApiService;
import com.example.btl.model.Data;
import com.example.btl.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText txtOldPassword, txtNewPassword, txtConfirmPassword;
    private TextView txtError;
    private Button btnCancel, btnSave;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        init();
        txtOldPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b == true) txtError.setText("");
            }
        });
        txtNewPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b == true) txtError.setText("");
            }
        });
        txtConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b == true) txtError.setText("");
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangePasswordActivity.this, HomeActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPassword = txtOldPassword.getText().toString();
                String newPassword = txtNewPassword.getText().toString();
                String confirmPassword = txtConfirmPassword.getText().toString();
                if(!oldPassword.equals(user.getPassword())){
                    txtError.setText("Mật khẩu hiện tại không chính xác!");
                }else if(!newPassword.equals(confirmPassword)){
                    txtError.setText("Xác nhận mật khẩu không chính xác!");
                }else{
                    txtError.setText("");
                    user.setPassword(newPassword);
                    ApiService.apiService.changePassword(user).enqueue(new Callback<Data>() {
                        @Override
                        public void onResponse(Call<Data> call, Response<Data> response) {
                            Data data = response.body();
                            if(data.isStatus() == true){
                                System.out.println("ok");
                                Intent intent = new Intent(ChangePasswordActivity.this, HomeActivity.class);
                                intent.putExtra("user", user);
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
    }
    private void init(){
        txtOldPassword = findViewById(R.id.txtOldPassword);
        txtNewPassword = findViewById(R.id.txtNewPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        txtError = findViewById(R.id.txtError);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
    }
}