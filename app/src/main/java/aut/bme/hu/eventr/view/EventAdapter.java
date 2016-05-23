package aut.bme.hu.eventr.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import aut.bme.hu.eventr.R;
import aut.bme.hu.eventr.model.EventModel;

class EventAdapter extends ArrayAdapter<EventModel> {

    public EventAdapter(Context context, int resource, List<EventModel> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.event_list_element, null);
        }

        EventModel data = getItem(position);
        if (data != null) {
            TextView dateText = (TextView) v.findViewById(R.id.dateRow);
            dateText.setText(data.getDate().toString());

            TextView titleText = (TextView) v.findViewById(R.id.titleRow);
            titleText.setText(data.getTitle().toString());
        }
        return v;
    }
}