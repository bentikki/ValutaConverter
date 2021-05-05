package com.example.valutaconverter.Presenters;

import com.example.valutaconverter.Models.Rate;

public abstract class PresenterBase {

    protected ViewContract view;

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

}
