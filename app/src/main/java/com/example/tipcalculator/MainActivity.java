package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {
    private TextView TipPercentage;
    private SeekBar seekBarTip;
    private EditText etBase;
    private TextView TipAmount;
    private TextView BillAmount;
    private RatingBar ratingBarTip;
    private RadioGroup radioGroup1;
    private double NewRate=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TipPercentage=(TextView) findViewById(R.id.TipPercentage);
        seekBarTip=(SeekBar) findViewById(R.id.seekBarTip);
        etBase=(EditText) findViewById(R.id.etBase);
        TipAmount=(TextView) findViewById(R.id.TipAmount);
        BillAmount=(TextView) findViewById(R.id.BillAmount);
        ratingBarTip = (RatingBar) findViewById(R.id.ratingBarTip);
        radioGroup1= (RadioGroup) findViewById(R.id.radioGroup1);


        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                case R.id.radioButton5:
                    NewRate=0.75;
                    break;
                case R.id.radioButton6:
                    NewRate=1;
                    break;
                case R.id.radioButton7:
                    NewRate=1.25;
                    break;
                case R.id.radioButton8:
                    NewRate=1.5;
                    break;
            }
            calculateTip();
            }
        });


        ratingBarTip.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                calculateTip();
            }
        });

        seekBarTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TipPercentage.setText(""+progress+"%");
                calculateTip();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        etBase.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
               calculateTip();
            }
        });
    }

    private void calculateTip() {
        if(etBase.getText().toString().isEmpty()){
            TipAmount.setText("");
            BillAmount.setText("");
            return;
        }
        String baseAmount = etBase.getText().toString();
        Double bA=Double.parseDouble(baseAmount);
        int tipPercentage = seekBarTip.getProgress();
        float Rating=ratingBarTip.getRating();
        double tP=bA*(tipPercentage*NewRate+Rating)/100;
        double tA=bA+tP;
        TipAmount.setText(String.format("%.2f", tP));
        BillAmount.setText(String.format("%.2f",tA));
    }
}
