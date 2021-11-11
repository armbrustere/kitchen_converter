package com.ethan.measurement_converter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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


    //Overrides the Conversion_Activity onCreate method
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_conv, container, false);

        //Add references to elements
        convert_from_text = (EditText) v.findViewById(R.id.convert_from);
        result = (TextView) v.findViewById(R.id.result);
        convert_from_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Placeholder here add value to something for conversion
                //probably need to convert from string to int
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        unit_from = (Spinner) v.findViewById(R.id.unit_from);
        unit_to = (Spinner) v.findViewById(R.id.unit_to);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.units_of_measurement, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit_from.setAdapter(adapter);
        unit_to.setAdapter(adapter);
//        unit_from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        return v;
    }

    private void convert()
    {
        String from_numString = convert_from_text.getText().toString();
        double from_numDoub;
        if(from_numString.equals(""))
        {
            from_numDoub = 0;
        }else
        {
            from_numDoub = Double.parseDouble(from_numString);

        }
    }


}