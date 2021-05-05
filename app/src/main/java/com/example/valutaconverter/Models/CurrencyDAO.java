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
}
