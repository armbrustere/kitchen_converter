package com.ethan.measurement_converter;

import androidx.annotation.RequiresApi;

import java.util.List;

import javax.measure.quantity.Quantity;
import javax.measure.quantity.Volume;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public class Conversion_Units {
    public double kg_perLb;
    public double ounce_perL;
    public static final Unit<Volume> TABLESPOON_US = NonSI.OUNCE_LIQUID_US.divide(2);
    public static final Unit<Volume> CUP_US = NonSI.OUNCE_LIQUID_US.times(8);


    @RequiresApi(30)
    public static final List<Unit<? extends Quantity>> UNITS = List.of(
            TABLESPOON_US,
            CUP_US,
            SI.KILOGRAM,
            NonSI.POUND
    );



    public Conversion_Units() {
        //Dry Measurements
        kg_perLb = 0.45359237;


        //Liquid Measurements
        ounce_perL = 33.8140227018;
    }

    public double getKg_perLb() {
        return kg_perLb;
    }

}