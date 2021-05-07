package com.example.valutaconverter.Presenters;

import com.example.valutaconverter.Models.DaoFactory;
import com.example.valutaconverter.Models.Rate;

import java.util.ArrayList;


/**
 * Presenter managing Currency rate functionality.
 *
 *
 * @author BTO
 * @version 1.0
 * @since 1.0
 */
public class CurrencyPresenter extends PresenterBase {

    /**
     * View Contract offered by CurrencyPresenter.
     *
     *
     * @author BTO
     * @version 1.0
     * @since 1.0
     */
    public interface HomeViewContract extends ViewContract{
        void rateItemClicked(String valutaName);
        void navigateToHistoricalPage(Rate rate);
    }

    private ArrayList<Rate> currencyRates;

    /**
     * Returns the current valuta rates as ArrayList<Rate> of Rate objects.
     *
     * @return ArrayList<Rate> with list of Rate objects.
     */
    public ArrayList<Rate> getCurrencyRates(){
        return this.currencyRates;
    }

    public CurrencyPresenter(HomeViewContract homeView){
        this.view = homeView;

        // Set DAO
        this.dao = DaoFactory.createDAO();
        this.currencyRates = this.dao.getCurrentRates();
    }

    /**
     * Gets the homeView to show historical data, from provided currency code.
     *
     * Must be called when an Currency Rate item has been selected.
     *
     * @param valutaName selected valuta code must be provided.
     */
    public void rateItemClicked(String valutaName){

        try{
            if(valutaName == null || valutaName.toUpperCase() == "DKK") throw new IllegalArgumentException();

            // Get Rate object matching provided name.
            Rate selectedValutaRate = this.dao.getRateByName(valutaName);
            if(selectedValutaRate == null) throw new NullPointerException();

            // Get homeView to display historical data.
            ((HomeViewContract)this.view).navigateToHistoricalPage(selectedValutaRate);
        }
        catch(IllegalArgumentException exception){
            this.showViewError("The provided valuta code is not valid.");
        }
        catch(NullPointerException exception){
            this.showViewError("No valuta rate found.");
        }
        catch(Exception exception){
            this.showViewError("An unexpected error occured.");
        }
    }



}
