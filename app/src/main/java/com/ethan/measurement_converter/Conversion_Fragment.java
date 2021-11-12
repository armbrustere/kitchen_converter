package com.ethan.measurement_converter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    private EditText convert_from_text;
    private Spinner unit_from;
    private TextView result;
    private Spinner unit_to;
    private Conversion_Unit cu;


    public Conversion_Fragment() {
        cu = new Conversion_Unit();
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
        convert_from_text = v.findViewById(R.id.convert_from);
        result = v.findViewById(R.id.result);
        unit_from = v.findViewById(R.id.unit_from);
        unit_to = v.findViewById(R.id.unit_to);

        //Add adapters to spinners for units
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.units_of_measurement, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit_from.setAdapter(adapter);
        unit_to.setAdapter(adapter);

        //Add listeners to elements
        convert_from_text.setOnEditorActionListener(this::onEditorAction);


        return v;
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        int keyCode = -1;
        double kgtoLB = cu.getKg_perLb();
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
        String from_numString = convert_from_text.getText().toString();
        double from_numDoub;
        double doubResult;
        double kgtoLB;
        String test;

        if (from_numString.equals("")) {
            from_numDoub = 0;
        } else {
            from_numDoub = Double.parseDouble(from_numString);
        }


        //Convert units
        if (unit_from.getSelectedItem().toString().equals("lb") && unit_to.getSelectedItem().toString().equals("kg")) {
            kgtoLB = cu.getKg_perLb();
            doubResult = (from_numDoub * kgtoLB);
            test = String.format(String.valueOf(doubResult), "%.2f");
            result.setText(test);
           // result.setText(doubResult.f);

        }
    }


}