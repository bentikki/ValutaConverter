package com.example.valutaconverter.Presenters;

import com.example.valutaconverter.Models.CurrencyDAO;
import com.example.valutaconverter.Models.CurrencyMock;
import com.example.valutaconverter.Models.Rate;

import java.util.ArrayList;
import java.util.List;

public class CurrencyPresenter {

    private ViewContract view;

    public interface ViewContract{

    }

    private CurrencyDAO dao;

    public CurrencyPresenter(ViewContract view){
        this.view = view;

        // Set DAO as Mock
        this.dao = new CurrencyMock();
    }

    public ArrayList<Rate> getCurrencyRates(){
        return this.dao.getCurrentRates();
    }


}
