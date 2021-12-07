package com.example.taxcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputEditText;

import soup.neumorphism.NeumorphCardView;

public class MainActivity extends AppCompatActivity {
    TextInputEditText Salary_Input;
    TextView tax_before_CESS, tax_after_CESS, tax_total, amount_taking_home;
    LinearLayout tax_print_out;
    Button button;
    NeumorphCardView card_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tax_print_out = findViewById(R.id.tax_print_out);
        Salary_Input = findViewById(R.id.Salary_Input);
        tax_before_CESS = findViewById(R.id.tax_before_CESS);
        tax_after_CESS = findViewById(R.id.tax_after_CESS);
        tax_total = findViewById(R.id.tax_total);
        amount_taking_home = findViewById(R.id.amount_taking_home);
        button = findViewById(R.id.button);
        card_start = findViewById(R.id.card_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(Salary_Input.getText().toString())) {
                    Salary_Input.setError("This Field can't be empty");
                } else {
                    tax_print_out.setVisibility(View.VISIBLE);
                    card_start.setVisibility(View.GONE);
                    InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);
                    Tax_Calculation();
                }
            }
        });


    }

    private void Tax_Calculation() {
        int tax_payable_salary = Integer.parseInt(Salary_Input.getText().toString());
        double tax = 0;
        int remaining = 0;
        if (tax_payable_salary <= 250000) {
            tax_total.setText(String.valueOf(tax));
            tax_before_CESS.setText(String.valueOf(0));
            tax_after_CESS.setText(String.valueOf(0));
            amount_taking_home.setText(String.valueOf(Salary_Input.getText()));
            return;

        } else if (tax_payable_salary < 500000) {
            tax_total.setText(String.valueOf(tax));
            tax_before_CESS.setText(String.valueOf(0));
            tax_after_CESS.setText(String.valueOf(0));
            amount_taking_home.setText(String.valueOf(Salary_Input.getText()));
            return;
        } else {
            if (tax_payable_salary <= 1000000) {
                tax = 12500;
                remaining = tax_payable_salary - 500000;
                tax = tax + (remaining * 0.2);


            } else {
                tax = 12500 + 100000;
                remaining = tax_payable_salary - 1000000;
                tax = tax + (remaining * 0.3);

            }

        }

        double totalTax_After_CESS = tax + tax * 0.04;
        tax_before_CESS.setText(String.valueOf(tax));
        tax_after_CESS.setText(String.valueOf(tax * 0.4));
        tax_total.setText(String.valueOf(totalTax_After_CESS));
        amount_taking_home.setText(String.valueOf((tax_payable_salary - totalTax_After_CESS)));


    }

}