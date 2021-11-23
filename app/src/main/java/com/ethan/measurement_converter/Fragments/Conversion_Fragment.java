package com.ethan.measurement_converter.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

import com.ethan.measurement_converter.Adapters.UnitMapAdapter;
import com.ethan.measurement_converter.Conversion_Units;
import com.ethan.measurement_converter.R;

import org.jscience.physics.amount.Amount;

import java.text.DecimalFormat;

import javax.measure.quantity.Quantity;
import javax.measure.unit.Unit;

public class Conversion_Fragment extends Fragment {
    private EditText amount_from;
    private TextView amount_to;
    private Spinner unit_from, unit_to;
    private Button F1_4, F1_3, F1_2;
    DecimalFormat df;
    private View v;

    //Overrides the Conversion_Activity onCreate method
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_conv, container, false);
        df = new DecimalFormat("###.##");
        //Add references to elements
        amount_from = v.findViewById(R.id.amount1);
        amount_to = v.findViewById(R.id.amount2);
        unit_from = v.findViewById(R.id.unit_from);
        unit_to = v.findViewById(R.id.unit_to);
        F1_2 = v.findViewById(R.id.Fraction1_2);
        F1_3 = v.findViewById(R.id.Fraction1_3);
        F1_4 = v.findViewById(R.id.Fraction1_4);
        setupArrayAdapters();
        setupEditListeners();
        setupFractionButtons();
        return v;
    }


    private void convert() {
        //Gets the unit from the corresponding spinners
        Unit<? extends Quantity> fromUnit = (Unit<? extends Quantity>) unit_from.getSelectedItem();
        Unit<? extends Quantity> toUnit = (Unit<? extends Quantity>) unit_to.getSelectedItem();

        //Gets the amount from the edittext element and sets up a formatter for rounding
        String fromString = amount_from.getText().toString();


        double a_from;

        //Accounts for startup when no Input has been entered
        if (fromString.equals("") || fromString.equals(".")) {
            a_from = 0;
        } else {
            a_from = Double.parseDouble(fromString);
        }

        Amount<? extends Quantity> from = Amount.valueOf(a_from, fromUnit);
        Amount<? extends Quantity> to = Amount.ZERO;
        try {
            if (fromUnit.isCompatible(toUnit)) {
                to = from.to(toUnit);
            }
        } catch (Exception e) {
            System.out.println("Not able to convert to this Unit!!");
        }

        double toValue = to.getEstimatedValue();
        amount_to.setText(df.format(toValue));


    }

    private void setupArrayAdapters() {
        UnitMapAdapter unitAdapter = new UnitMapAdapter(requireActivity().getApplicationContext(), Conversion_Units.getAll());
        unit_from.setAdapter(unitAdapter);
        unit_to.setAdapter(unitAdapter);
    }

    private void setupFractionButtons() {
        setupFractionListener(F1_2.getId(), 0.50f);
        setupFractionListener(F1_3.getId(), 0.33f);
        setupFractionListener(F1_4.getId(), 0.25f);
    }

    private void setupEditListeners() {
        //Add listeners to edittext and spinners
        amount_from.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                convert();

            }
        });

        unit_from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        unit_to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void setupFractionListener(final int resId, final float frac_val) {
        Button Fbutton = v.findViewById(resId);
        Fbutton.setOnClickListener(v -> addFraction(frac_val));
    }

    private void addFraction(final float frac_value) {
        String amount_String = amount_from.getText().toString();
        double val = amount_String.isEmpty() ? 0 : Double.parseDouble(amount_String);
        val += frac_value;
        //Fixes issues when using ⅓ button
        double fraction = val % 1;
        if (fraction <= 0.01 || fraction >= 0.99) {
            val = Math.round(val);
        }
        amount_from.setText(df.format(val));


    }
}