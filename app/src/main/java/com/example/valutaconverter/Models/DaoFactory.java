package com.example.valutaconverter.Models;

public class DaoFactory {

    public static CurrencyDAO createDAO(){

        CurrencyDAO dataAccessObjectInUse;
        dataAccessObjectInUse = new CurrencyMock();

        return dataAccessObjectInUse;
    }

}
