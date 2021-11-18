package com.ethan.measurement_converter.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ethan.measurement_converter.Conversion_Units;

import java.util.List;

import javax.measure.quantity.Quantity;
import javax.measure.unit.Unit;

public class UnitMapAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final List<Unit<? extends Quantity>> units;

    public UnitMapAdapter(@NonNull Context context, List<Unit<? extends Quantity>> units) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.units = units;
    }

    @Override
    public int getCount() {
        return units.size();
    }

    @Override
    public Object getItem(int position) {
        return units.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return constructView(position, convertView, parent, android.R.layout.simple_spinner_item);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return constructView(position, convertView, parent, android.R.layout.simple_spinner_dropdown_item);
    }


    private View constructView(int position, @Nullable View convertView, @NonNull ViewGroup parent, @LayoutRes int resource) {
        TextView view;
        if (convertView == null) {
            view = (TextView) inflater.inflate(resource, parent, false);
        } else {
            view = (TextView) convertView;
        }
        Unit<? extends Quantity> unit = (Unit<? extends Quantity>) getItem(position);
        view.setText(Conversion_Units.unitToString(unit));

        return view;
    }
}