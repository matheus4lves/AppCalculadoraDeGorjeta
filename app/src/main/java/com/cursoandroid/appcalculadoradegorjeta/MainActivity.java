package com.cursoandroid.appcalculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    // Create the variables
    private EditText editBillValue;
    private TextView textTipPercentage, textTipTotal, textBillTotal;
    private SeekBar seekBarTipPercentage;
    private double percentage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the variables
        editBillValue = findViewById(R.id.editBillValue);
        textTipPercentage = findViewById(R.id.textTipPercentage);
        textTipTotal = findViewById(R.id.textTipTotal);
        textBillTotal = findViewById(R.id.textBillTotal);
        seekBarTipPercentage = findViewById(R.id.seekBarTipPercentage);

        seekBarTipPercentage.setMax(100);

        // Add a listener to the SeekBar
        seekBarTipPercentage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // instantiate an anonymous class that implements the OnSeekBarChangeListener interface

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percentage = progress;
                textTipPercentage.setText((int) percentage + " %");
                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void calculate() {
        // recover the inputed bill value
        String inputedValue = editBillValue.getText().toString();

        // make sure the user input a value
        if (inputedValue == null || inputedValue.equals("")) {
            Toast.makeText(getApplicationContext(), "Digite o valor da conta!", Toast.LENGTH_LONG).show();
        } else {
            // convert the recovered value into a double
            double bill = Double.parseDouble(inputedValue);

            // calculate and display the tip
            double tip = bill * (percentage / 100);
            textTipTotal.setText("R$ " + tip);

            // calculate and display the total of the bill
            double totalBill = bill + tip;
            textBillTotal.setText("R$ " + totalBill);
        }
    }
}