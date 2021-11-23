package com.ethan.measurement_converter;

import static java.util.Map.entry;

import java.util.List;
import java.util.Map;

import javax.measure.quantity.Quantity;
import javax.measure.quantity.Volume;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public class Conversion_Units {
    public static final Unit<Volume> TABLESPOON_US = NonSI.OUNCE_LIQUID_US.divide(2);
    public static final Unit<Volume> TEASPOON_US = TABLESPOON_US.divide(3);
    public static final Unit<Volume> CUP_US = NonSI.OUNCE_LIQUID_US.times(8);


    public static final List<Unit<? extends Quantity>> UNITS = List.of(
            TABLESPOON_US,
            TEASPOON_US,
            CUP_US,
            SI.KILOGRAM,
            SI.GRAM,
            NonSI.POUND,
            NonSI.OUNCE,
            NonSI.POUND,
            SI.MILLI(NonSI.LITER),
            NonSI.LITER,
            NonSI.OUNCE_LIQUID_US,
            NonSI.OUNCE_LIQUID_UK

    );

    public static List<Unit<? extends Quantity>> getAll() {
        return UNITS;
    }

    public static final Map<Unit<? extends Quantity>, String> UNIT_TO_STRING = Map.ofEntries(
            entry(TABLESPOON_US, "tbsp(US)"),
            entry(TEASPOON_US, "tsp(US)"),
            entry(CUP_US, "cup (US)")
            );


    public static String unitToString(Unit<? extends Quantity> unit) {
        String s = UNIT_TO_STRING.get(unit);
        return s == null ? unit.toString() : s;

    }
}