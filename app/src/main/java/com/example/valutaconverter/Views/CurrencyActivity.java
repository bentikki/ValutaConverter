package com.example.valutaconverter.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valutaconverter.Models.Rate;
import com.example.valutaconverter.Presenters.CurrencyPresenter;
import com.example.valutaconverter.R;

import java.util.ArrayList;
import java.util.List;

public class CurrencyActivity extends AppCompatActivity implements CurrencyPresenter.HomeViewContract {

    // Presenter
    CurrencyPresenter presenter;

    public CurrencyActivity(){
        this.presenter = new CurrencyPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set origin text
        TextView subheadingTextView = findViewById(R.id.subheadingTextView);
        subheadingTextView.setText("(Relative to " + presenter.getOriginRate().getName().toUpperCase() + ")");

        // Get data objects
        ArrayList<Rate> rateList = this.presenter.getCurrencyRates();

        // Get Rate View Adaptor
        RateListAdapter rateListAdapter = new RateListAdapter(this, rateList);

        // Implement Adaptor to View
        ListView valutaSelectListView = (ListView) findViewById(R.id.valutaSelectListView);
        valutaSelectListView.setAdapter(rateListAdapter);

        // Set OnClickListener for view list items
        valutaSelectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Rate rateSelected = (Rate)valutaSelectListView.getItemAtPosition(i);
                rateItemClicked(rateSelected.getName());
            }
        });
    }

    public void rateItemClicked(String valutaName){
        presenter.rateItemClicked(valutaName);
    }

    @Override
    public void navigateToHistoricalPage(Rate rate) {
        Intent navigateToHistoryIntent = new Intent(this, HistoricalDataActivity.class);
        navigateToHistoryIntent.putExtra("RATE_VALUTA_CODE", rate.getName());
        startActivity(navigateToHistoryIntent);
    }

    @Override
    public void showError(String errorMessage) {
        Context context = getApplicationContext();
        CharSequence text = errorMessage;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


}