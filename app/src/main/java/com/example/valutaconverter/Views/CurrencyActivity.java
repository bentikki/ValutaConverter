package com.example.valutaconverter.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.valutaconverter.Models.Rate;
import com.example.valutaconverter.Presenters.CurrencyPresenter;
import com.example.valutaconverter.R;

import java.util.ArrayList;
import java.util.List;

public class CurrencyActivity extends AppCompatActivity implements CurrencyPresenter.ViewContract {

    CurrencyPresenter presenter;

    public CurrencyActivity(){
        this.presenter = new CurrencyPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get data objects
        ArrayList<Rate> rateList = this.presenter.getCurrencyRates();

        // Get Rate View Adaptor
        RateListAdapter rateListAdapter = new RateListAdapter(this, rateList);

        // Implement Adaptor to View
        ListView valutaSelectListView = (ListView) findViewById(R.id.valutaSelectListView);
        valutaSelectListView.setAdapter(rateListAdapter);

    }
}