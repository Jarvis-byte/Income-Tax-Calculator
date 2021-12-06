package com.example.taxcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextInputEditText Salary_Input;
    TextView tax_before_CESS, tax_after_CESS, tax_total, amount_taking_home;
    LinearLayout tax_print_out;
    Button button;

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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tax_print_out.setVisibility(View.VISIBLE);
                Tax_Calculation();

            }
        });


    }

    private void Tax_Calculation() {
        int tax_payable_salary = Integer.parseInt(Salary_Input.getText().toString());
        double tax = 0;
        int remaining = 0;
        if (tax_payable_salary <= 250000) {
            System.out.println("Tax Amount:-\t" + tax);
            tax_total.append(String.valueOf(tax));

            return;

        } else if (tax_payable_salary < 500000) {
            System.out.println("Your tax amount 12,500 will be paid by government Rebates");
            tax_total.append(String.valueOf(tax));
            return;
        } else {
            if (tax_payable_salary <= 1000000) {
                tax = 12500;
                System.out.println("Tax for Rs 2,50,000 to Rs 5,00,000:-\t" + tax);
                remaining = tax_payable_salary - 500000;
                System.out.println("Tax for Rs " + remaining + ":-\t" + remaining * 0.2);
                tax = tax + (remaining * 0.2);


            } else {
                tax = 12500 + 100000;
                remaining = tax_payable_salary - 1000000;
                tax = tax + (remaining * 0.3);


            }

        }

        double totalTax_After_CESS = tax + tax * 0.04;
        tax_before_CESS.append(String.valueOf(tax));
        tax_after_CESS.append(String.valueOf(tax * 0.4));
        tax_total.append(String.valueOf(totalTax_After_CESS));
        amount_taking_home.append(String.valueOf((tax_payable_salary - totalTax_After_CESS)));


    }

}