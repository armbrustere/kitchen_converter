package com.ethan.measurement_converter;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Conversion_Fragment extends Fragment {
    private EditText amount_from;
    private TextView amount_to;
    private Spinner unit_from, unit_to;
    private final Conversion_Units cu;



    public Conversion_Fragment() {
        cu = new Conversion_Units();
    }


    //Overrides the Conversion_Activity onCreate method
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_conv, container, false);

        //Add references to elements
        amount_from = v.findViewById(R.id.unit1);
        amount_to = v.findViewById(R.id.unit2);
        unit_from = v.findViewById(R.id.unit_from);
        unit_to = v.findViewById(R.id.unit_to);

        //Add adapters to spinners for units
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.units_of_measurement, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit_from.setAdapter(adapter);
        unit_to.setAdapter(adapter);

        //Add listeners to elements
        amount_from.setOnEditorActionListener(this::onEditorAction);


        return v;
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        int keyCode = -1;
        if (event != null) {
            keyCode = event.getKeyCode();
        }
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED || keyCode == KeyEvent.KEYCODE_ENTER) {
            convert();
//            System.out.println("--------------------------------");
//            System.out.println("THe enter key is PRESSSEDDD!!!!");
//            System.out.println(kgtoLB);
//            System.out.println("--------------------------------");

        }
        return false;
    }


    private void convert() {
        String amount_fromString = amount_from.getText().toString();
        double amount_from;

        double result;
        double kgtoLB = cu.getKg_perLb();
        String test;
        

        if (amount_fromString.equals("")) {
            amount_from = 0;
        } else {
            amount_from = Double.parseDouble(amount_fromString);
        }
        
        //Amount<? extends Quantity> from =Amount.valueOf(from_numDoub, kgtoLB);
       // Amount<? extends Quantity> to;

        //Convert units
        if (unit_from.getSelectedItem().toString().equals("kg")  && unit_to.getSelectedItem().toString().equals("lb") ) {
            //kgtoLB = Conversion_Units.UNITS.get(0);
            //to = kgtoLB.times(from_numDoub);

            result = (amount_from * kgtoLB);
            test = String.format(String.valueOf(result), "%.2f");
            amount_to.setText(test);
            System.out.println(test);


        }
    }


}