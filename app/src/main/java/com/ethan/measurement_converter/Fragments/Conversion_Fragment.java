package com.ethan.measurement_converter.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
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

    //Overrides the Conversion_Activity onCreate method
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_conv, container, false);
        //Add references to elements
        amount_from = v.findViewById(R.id.amount1);
        amount_to = v.findViewById(R.id.amount2);
        unit_from = v.findViewById(R.id.unit_from);
        unit_to = v.findViewById(R.id.unit_to);

        setupArrayAdapters();
        setupEditListeners();


        return v;
    }


    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        int keyCode = -1;
        if (event != null) {
            keyCode = event.getKeyCode();
        }
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED || keyCode == KeyEvent.KEYCODE_ENTER) {
            convert();
        }
        return false;
    }


    private void convert() {
        //Gets the unit from the corresponding spinners
        Unit<? extends Quantity> fromUnit = (Unit<? extends Quantity>) unit_from.getSelectedItem();
        Unit<? extends Quantity> toUnit = (Unit<? extends Quantity>) unit_to.getSelectedItem();

        //Gets the amount from the edittext element and sets up a formatter for rounding
        String fromString = amount_from.getText().toString();
        DecimalFormat df = new DecimalFormat("###.##");

        double a_from;

        //Accounts for startup when no Input has been entered
        if (fromString.equals("")) {
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

    private void setupEditListeners() {
        //Add listeners to elements
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
        amount_from.setOnEditorActionListener(this::onEditorAction);
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

}