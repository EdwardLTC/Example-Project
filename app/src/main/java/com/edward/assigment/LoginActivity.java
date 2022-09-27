package com.edward.assigment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.edward.assigment.dao.DataAccesObject;
import com.edward.assigment.modal.Admin;

public class LoginActivity extends AppCompatActivity {
    EditText user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        DataAccesObject dataAccesObject = new DataAccesObject(this);
        findViewById(R.id.login).setOnClickListener(view -> {
            Admin admin = dataAccesObject.HandleLoginForAdmin(user.getText().toString(), pass.getText().toString());
            if (admin == null) {
                Toast.makeText(LoginActivity.this, "Error Login", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("Object",admin);
                startActivity(intent);
                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}