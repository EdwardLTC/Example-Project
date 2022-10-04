package com.edward.assigment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.edward.assigment.dao.DataAccesObject;
import com.edward.assigment.modal.Admin;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {
    EditText user, pass;
    DataAccesObject dataAccesObject = new DataAccesObject(this);
    SharedPreferences shp;
    SharedPreferences.Editor shpEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);

        CheckLogin();
        findViewById(R.id.login).setOnClickListener(view -> DoLogin(user.getText().toString(),pass.getText().toString()));
    }
    public void DoLogin(String username, String pass) {
        try {
            Admin admin = dataAccesObject.HandleLoginForAdmin(Integer.parseInt(username), pass);
            if (admin == null) {
                Toast.makeText(LoginActivity.this, "Error Login", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("Object", admin);
                Gson gson = new Gson();
                String json = gson.toJson(admin);
                shpEditor = shp.edit();
                shpEditor.putString("Object", json);
                shpEditor.commit();
                startActivity(intent);
                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                finish();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void CheckLogin() {
        if (shp == null)
            shp = getSharedPreferences("myPreferences", MODE_PRIVATE);

        Gson gson = new Gson();
        String object = shp.getString("Object", "");
        Admin obj = gson.fromJson(object, Admin.class);

        if (object != null && !object.equals("")) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            i.putExtra("Object",obj);
            startActivity(i);
            finish();
        }
    }
}