package com.example.mymoney;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextFirstDeposit, editTextMonthlyDeposit, editTextTime, editTextAnnualInterest;
    private Button mainButtonCalculate;
    private TextView textViewResult;
    private ImageView setting_IMG_img;
    private RadioGroup radioGroupCurrency;
    private RadioButton radioUSD, radioEUR, radioILS;
    private double firstDeposit, monthlyDeposit, annualInterest;
    private int numYears;
    String currency = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFirstDeposit = findViewById(R.id.editTextFirstDeposit);
        editTextMonthlyDeposit = findViewById(R.id.editTextMonthlyDeposit);
        editTextTime = findViewById(R.id.editTextTime);
        editTextAnnualInterest = findViewById(R.id.editTextAnnualInterest);
        mainButtonCalculate = findViewById(R.id.mainButtonCalculate);
        textViewResult = findViewById(R.id.textViewResult);
        setting_IMG_img = findViewById(R.id.setting_IMG_img);
        radioGroupCurrency = findViewById(R.id.radioGroupCurrency);
        radioUSD = findViewById(R.id.radioUSD);
        radioEUR = findViewById(R.id.radioEUR);
        radioILS = findViewById(R.id.radioILS);

        mainButtonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValues();
                double answer = calculateInvestment(firstDeposit, monthlyDeposit, numYears, annualInterest, currency);
                openAnswerPage(answer);
            }
        });

        setting_IMG_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                finish();
            }
        });
    }

    private void setValues() {
        try {
            firstDeposit = Double.parseDouble(editTextFirstDeposit.getText().toString());
            monthlyDeposit = Double.parseDouble(editTextMonthlyDeposit.getText().toString());
            numYears = Integer.parseInt(editTextTime.getText().toString());
            annualInterest = Double.parseDouble(editTextAnnualInterest.getText().toString());

            int selectedId = radioGroupCurrency.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton radioButton = findViewById(selectedId);
                currency = radioButton.getText().toString();
            } else {
                Toast.makeText(this, "Please select valid value", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Log.e("NumberFormatException", "Error parsing some value: " + e.getMessage());
            Toast.makeText(this, "Please enter a valid numeric values", Toast.LENGTH_SHORT).show();
            return;
        } catch (NullPointerException e) {
            Toast.makeText(this, "Please fill values in all fields", Toast.LENGTH_SHORT).show();
        }
    }


    private void openAnswerPage(double answer) {
        Intent intent = new Intent(this, AnswerActivity.class);
        intent.putExtra(AnswerActivity.KEY_ANSWER,answer);
        startActivity(intent);
        finish();
    }


    public double calculateInvestment(double firstDeposit, double monthlyDeposit, int numYears, double annualInterest, String currency) {
        // Convert annual interest to monthly interest rate
        double monthlyInterestRate = annualInterest / 12 / 100;

        // Initialize the final sum with the initial deposit
        double finalSum = firstDeposit;

        // Calculate the final sum after each month's deposit
        for (int month = 0; month < numYears * 12; month++) {
            finalSum *= (1 + monthlyInterestRate);
            finalSum += monthlyDeposit;
        }

        // Return the final sum based on the chosen currency
        if (currency.equals("ILS")) {
            // Assuming 1 ILS = 0.3 USD and 1 ILS = 0.25 EUR for simplicity
            finalSum *= 0.3;
        } else if (currency.equals("EUR")) {
            // Assuming 1 EUR = 1.2 USD and 1 EUR = 4 ILS for simplicity
            finalSum *= 1.2;
        }
        // For USD, no conversion needed
        return finalSum;
    }

}
