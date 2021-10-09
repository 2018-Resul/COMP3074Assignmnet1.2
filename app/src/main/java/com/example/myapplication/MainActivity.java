package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    //input variables
    EditText workedHoursInput;
    EditText hourlyRateInput;

    // output variables
    TextView totalPayTextView, taxPayableTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing inputs
        workedHoursInput = findViewById(R.id.editTextWorkedHours);
        hourlyRateInput = findViewById(R.id.editTextHourlyRate);


       // outputs
        totalPayTextView = findViewById(R.id.editText_total_pay);
        taxPayableTextView = findViewById(R.id.editText_tax_payable);

    }

    // this method will be called by  clicking on the button
    public void calculateTaxAndPay(View view) {

        try {

            //  taking input from the user and converting the string into double
            double HoursWorked = Double.parseDouble(workedHoursInput.getText().toString());
            double hourlyRate = Double.parseDouble(hourlyRateInput.getText().toString());

            // initializing variables to display on the output
            double pay = 0;
            double tax = 0;

            // calculation method of pay and tax as it is given in the instruction.
            if (HoursWorked <= 40) {
                pay = HoursWorked * hourlyRate;

            } else {
                pay = (40 * hourlyRate)+ ((HoursWorked - 40) * hourlyRate*1.5);

            }
            tax = pay * 0.18;

            // displaying the output to the user and showing with 2 decimal numbers.

            totalPayTextView.setText(String.format("%.2f", pay));
            taxPayableTextView.setText(String.format("%.2f", tax));

        } catch (Exception e) {

            // warning the user that it is needed to be given inputs.
           TextView err = findViewById(R.id.error);
           err.setText(" Please enter worked hours and hourly payment");
           totalPayTextView.setText("N/A");
           taxPayableTextView.setText("N/A");

        }finally {
           // refreshing the page in 3 seconds
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    TextView err = findViewById(R.id.error);
                    err.setText("");
                }
            }, 3000);

        }
    }

    // rendering the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //implementing functionality when items clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_about_owner:
                // startActivity method is used to navigate from About activity to  main activity
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}