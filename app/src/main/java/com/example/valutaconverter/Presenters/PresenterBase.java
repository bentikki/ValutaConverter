package com.example.valutaconverter.Presenters;

import com.example.valutaconverter.Models.CurrencyDAO;
import com.example.valutaconverter.Models.Rate;

public abstract class PresenterBase {

    protected ViewContract view;
    protected CurrencyDAO dao;

    public interface ViewContract{
        void showError(String errorMessage);
    }

    /**
     * Shows error message on homeView
     *
     * @param errorMessage Message to display.
     */
    protected void showViewError(String errorMessage){
        this.view.showError(errorMessage);
    }

    /**
     * Returns the rate which is used as basis.
     *
     * @return Rate object used as basis rate.
     */
    public Rate getOriginRate(){
        return this.dao.getOriginRate();
    }
}
