package com.example.valutaconverter.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.valutaconverter.Models.Rate;
import com.example.valutaconverter.Presenters.HistoricalDataPresenter;
import com.example.valutaconverter.R;

import java.util.ArrayList;

public class HistoricalDataActivity extends AppCompatActivity implements HistoricalDataPresenter.HistoricalViewContract {

    private HistoricalDataPresenter presenter;

    // Element binding objects
    private TextView valutaHeading;
    private EditText editTextValutaDKK, editTextValutaTarget;


    public HistoricalDataActivity(){
        this.presenter = new HistoricalDataPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical);

        // Get passed intent
        Intent intentPassed = getIntent();
        String valutaCode = intentPassed.getStringExtra("RATE_VALUTA_CODE");

        // Set selected Rate object in presenter.
        presenter.setSelectedRate(valutaCode);

        // Bind from view
        valutaHeading = (TextView) findViewById(R.id.valutaHeading);
        editTextValutaDKK = (EditText) findViewById(R.id.editTextValutaDKK);
        editTextValutaTarget = (EditText) findViewById(R.id.editTextValutaTarget);

        // Set inital statics
        Rate selectedValuta = this.presenter.getSelectedRate();
        valutaHeading.setText(selectedValuta.getName() + " - " + selectedValuta.getCurrentRate());

        editTextValutaTarget.setHint(selectedValuta.getName());


        // Event listening
        editTextValutaDKK.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if(editTextValutaDKK.hasFocus()){
                    presenter.originalTextChanged(s.toString());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });


        editTextValutaTarget.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if(editTextValutaTarget.hasFocus()){
                    presenter.targetTextChanged(s.toString());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });



    }

    @Override
    public void showError(String errorMessage) {
        Context context = getApplicationContext();
        CharSequence text = errorMessage;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void updateOriginalEditText(String updateValue) {
        this.editTextValutaDKK.setText(updateValue);
    }

    @Override
    public void updateTargetEditText(String updateValue) {
        this.editTextValutaTarget.setText(updateValue);
    }
}
