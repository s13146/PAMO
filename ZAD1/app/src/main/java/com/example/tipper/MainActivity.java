package com.example.tipper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import com.example.tipper.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    RecipeFragment recipeFragment = new RecipeFragment();
    CaloriesFragment caloriesFragment = new CaloriesFragment();
    CalculateFragment calculateFragment = new CalculateFragment();
    private EditText weightEdit;
    private EditText growthEdit;
    private TextView bmiTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass onCreate
        setContentView(R.layout.activity_main); // inflate the GUI

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.bmi_card:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, calculateFragment).commit();
                        return true;
                    case R.id.calories_card:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, caloriesFragment).commit();
                        return true;
                    case R.id.recipe_card:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, recipeFragment).commit();
                        return true;
                }
                return false;
            }

        });
    }
    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
