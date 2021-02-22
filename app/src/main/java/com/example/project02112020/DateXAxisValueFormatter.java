package com.example.project02112020;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

class DateXAxisValueFormatter extends ValueFormatter {
    ArrayList<Long> dates;

    public DateXAxisValueFormatter(ArrayList<Long> dates) {
        this.dates = dates;
    }

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        int idx = (int)value;

        java.util.Date time=new java.util.Date((dates.get(idx) + 3*3600)*1000);

        String pattern = "HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


        return String.valueOf(simpleDateFormat.format(time));
    }
}
