package com.example.nutrilab.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nutrilab.R;

public class DetailActivity extends AppCompatActivity {
    private TextView txtFoodName, txtFoodInformation, txtCalorie, txtSugar, txtCarbo, txtFat, txtProtein;
    private void initUI(){
        txtFoodName = findViewById(R.id.txt_food_name);
        txtFoodInformation = findViewById(R.id.txt_food_information);
        txtCalorie = findViewById(R.id.calories);
        txtSugar = findViewById(R.id.glucose);
        txtCarbo = findViewById(R.id.carbohydrate);
        txtFat = findViewById(R.id.fat);
        txtProtein = findViewById(R.id.protein);


    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initUI();
        Intent intent = getIntent();
        if (intent != null) {
            String foodName = intent.getStringExtra("foodName");
            String foodInformation = intent.getStringExtra("foodInformation");
            String calorie = intent.getStringExtra("calorie");
            String sugar = intent.getStringExtra("sugar");
            String carbohydrate = intent.getStringExtra("carbohydrate");
            String fat = intent.getStringExtra("fat");
            String protein = intent.getStringExtra("protein");


            txtFoodName.setText(foodName);
            txtFoodInformation.setText(foodInformation);
            txtCalorie.setText(calorie);
            txtSugar.setText(sugar);
            txtCarbo.setText(carbohydrate);
            txtFat.setText(fat);
            txtProtein.setText(protein);

        } else {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }
    }
}
