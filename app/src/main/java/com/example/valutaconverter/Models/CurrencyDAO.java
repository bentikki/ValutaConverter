package com.example.valutaconverter.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface used to get Currency rates.
 *
 * DAO setup.
 *
 * @author BTO
 * @version 1.0
 * @since 1.0
 */
public interface CurrencyDAO {

    /**
     * Returns the current currency rates as List<Rate>.
     *
     * @return List<Rate> of current currency rates.
     */
    ArrayList<Rate> getCurrentRates();

    /**
     * Returns the rate object, matching the provided name.
     *
     * @param valutaName String containing valuta code, for the requested Rate object.
     * @return Rate Where Rate.Name matches the provided String.
     */
    Rate getRateByName(String valutaName);

    /**
     * Returns the historical rates, from provided Rate object, as ArrayList<ValutaHistory>.
     *
     * @param rate The valuta rate of whcih historical data is needed.
     * @return List<ValutaHistory> Containing historical data.
     */
    ArrayList<ValutaHistory> getHistoricalRates(Rate rate);

}
