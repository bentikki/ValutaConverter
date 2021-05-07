package com.example.valutaconverter.Models;

public class DaoFactory {

    private static final CurrencyDAO DAO_IN_USE = new CurrencyLayerApiDAO();

    public static CurrencyDAO createDAO(){
        CurrencyDAO dataAccessObjectInUse;
        dataAccessObjectInUse = DAO_IN_USE;

        return dataAccessObjectInUse;
    }

}
