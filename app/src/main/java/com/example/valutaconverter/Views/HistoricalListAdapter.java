package com.example.valutaconverter.Views;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.valutaconverter.Models.Rate;
import com.example.valutaconverter.Models.ValutaHistory;
import com.example.valutaconverter.R;

import java.util.ArrayList;


/**
 * Adapter to displat list item from ValutaHistory object.
 *
 * @author BTO
 * @version 1.0
 * @since 1.0
 */
public class HistoricalListAdapter extends ArrayAdapter<ValutaHistory> {

    public HistoricalListAdapter(Context context, ArrayList<ValutaHistory> valutaHistories){
        super(context, 0, valutaHistories); // Calls super with List of Rates.
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        ValutaHistory historyItem = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.valutahistorylist_item, parent, false);
        }

        // Lookup view for data population
        TextView valutaHistoryDate = (TextView) convertView.findViewById(R.id.valutaHistoryDate);
        TextView valutaHistoryRate = (TextView) convertView.findViewById(R.id.valutaHistoryRate);

        // Populate the data into the template view using the data object
        valutaHistoryDate.setText(historyItem.getDate());
        valutaHistoryRate.setText(String.valueOf(historyItem.getValutaRate().getCurrentRate()));

        // Return the completed view to render on screen
        return convertView;
    }

}
