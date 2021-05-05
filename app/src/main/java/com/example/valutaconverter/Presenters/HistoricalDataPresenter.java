package com.example.valutaconverter.Presenters;

import com.example.valutaconverter.Models.CurrencyDAO;
import com.example.valutaconverter.Models.CurrencyMock;
import com.example.valutaconverter.Models.DaoFactory;
import com.example.valutaconverter.Models.Rate;

import java.util.ArrayList;

/**
 * Presenter managing historical currency data functionality.
 *
 *
 * @author BTO
 * @version 1.0
 * @since 1.0
 */
public class HistoricalDataPresenter extends PresenterBase{

    /**
     * View Contract offered by CurrencyPresenter.
     *
     *
     * @author BTO
     * @version 1.0
     * @since 1.0
     */
    public interface HistoricalViewContract extends PresenterBase.ViewContract {
        void updateOriginalEditText(String updateValue);
        void updateTargetEditText(String updateValue);
    }

    private Rate selectedRate;
    private CurrencyDAO dao;

    public Rate getSelectedRate() {
        return selectedRate;
    }

    public HistoricalDataPresenter(ViewContract view){
        this.view = view;

        // Set current DAO
        this.dao = DaoFactory.createDAO();
    }

    /**
     * Sets selected Rate object from string valuta code.
     * Calls error message on view if selectedValutaCode is not a viable Valuta Code.
     *
     * @param selectedValutaCode valuta code for requested Rate object.
     */
    public void setSelectedRate(String selectedValutaCode){

        try{
            // Check for null in selectedValutaCode.
            if(selectedValutaCode == null) throw new IllegalArgumentException();

            // Trim selectedValutaCode String
            selectedValutaCode = selectedValutaCode.trim();

            // Get selected Rate item from DAO
            Rate requestedRate = this.dao.getRateByName(selectedValutaCode);

            // Check that the requested rate exists
            if(requestedRate == null) throw new NullPointerException();

            this.selectedRate = requestedRate;
        }
        catch (IllegalArgumentException e){
            this.showViewError("No valuta code provided.");
        }
        catch (NullPointerException e){
            this.showViewError("Could not find valuta rate from provided valuta code.");
        }
        catch(Exception e){
            this.showViewError("An unexpected error occured.");
        }


    }

    /**
     * Gets called when orignal valuta text value has changed.
     * Converts original valuta to target valuta, and updates fields on view.
     *
     * @param newValue new value in the changed text field.
     */
    public void originalTextChanged(String newValue){

        // Only run if newValue is not null or empty (it has changed)
        if(newValue != null && !newValue.isEmpty()) {

            try{
                double valueToExchange;
                double valueExchangeRate;
                double valueResult;

                valueToExchange = Double.parseDouble(newValue);
                valueExchangeRate = this.selectedRate.getCurrentRate();

                valueResult = valueToExchange / valueExchangeRate;

                ((HistoricalViewContract)this.view).updateTargetEditText(String.valueOf(valueResult));
            }
            catch(Exception e){
                this.showViewError("An unexpected error occured.");
            }

        }
        else{
            // newValue is empty - the input is null or empty

            // Empty the target
            ((HistoricalViewContract)this.view).updateTargetEditText("");
        }
    }

    /**
     * Gets called when target valuta text value has changed.
     * Converts target valuta to original valuta, and updates fields on view.
     *
     * @param newValue new value in the changed text field.
     */
    public void targetTextChanged(String newValue){

        // Only run if newValue is not null or empty (it has changed)
        if(newValue != null && !newValue.isEmpty()) {

            try{
                double valueToExchange;
                double valueExchangeRate;
                double valueResult;

                valueToExchange = Double.parseDouble(newValue);
                valueExchangeRate = this.selectedRate.getCurrentRate();

                valueResult = valueToExchange * valueExchangeRate;

                ((HistoricalViewContract)this.view).updateOriginalEditText(String.valueOf(valueResult));
            }
            catch(Exception e){
                this.showViewError("An unexpected error occured.");
            }

        }
        else{
            // newValue is empty - the input is null or empty

            // Empty the target
            ((HistoricalViewContract)this.view).updateOriginalEditText("");
        }

    }


}
