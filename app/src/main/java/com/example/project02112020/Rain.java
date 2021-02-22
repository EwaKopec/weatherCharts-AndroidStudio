package com.example.project02112020;

public class Rain {
    private String hours3;

    public String get3h ()
    {
        return hours3;
    }

    public void set3h (String h)
    {
        this.hours3 = h;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [3h = "+hours3+"]";
    }
}
