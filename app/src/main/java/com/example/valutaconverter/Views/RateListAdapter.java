package com.example.valutaconverter.Views;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.valutaconverter.Models.Rate;
import com.example.valutaconverter.R;

import java.util.ArrayList;


/**
 * Adapter to displat list item from Rate object.
 *
 * @author BTO
 * @version 1.0
 * @since 1.0
 */
public class RateListAdapter extends ArrayAdapter<Rate> {

    public RateListAdapter(Context context, ArrayList<Rate> rates){
        super(context, 0, rates); // Calls super with List of Rates.
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Rate rate = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ratelist_item, parent, false);
        }

        // Lookup view for data population
        TextView rateName = (TextView) convertView.findViewById(R.id.rateName);
        TextView rateCurrentRate = (TextView) convertView.findViewById(R.id.rateCurrentRate);

        // Populate the data into the template view using the data object
        rateName.setText(rate.getName());
        rateCurrentRate.setText(String.valueOf(rate.getCurrentRate()));

        // Return the completed view to render on screen
        return convertView;
    }

}
