package com.example.project02112020;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private RadioButton tempRB;
    private RadioButton pressureRB;
    private RadioButton windRB;
    private RadioButton cloudsRB;
    LineChart chart1;
    Forecast forecast;
    List[] list;

    float maxToInit = 0;
    float minToInit = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempRB = findViewById(R.id.tempRB);
        pressureRB = findViewById(R.id.pressRB);
        windRB = findViewById(R.id.windRB);
        cloudsRB = findViewById(R.id.cloudsRB);

        chart1 = findViewById(R.id.chart1);

        tempRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempRB.setChecked(true);
                pressureRB.setChecked(false);
                windRB.setChecked(false);
                cloudsRB.setChecked(false);
                test();
            }
        });

        pressureRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressureRB.setChecked(true);
                tempRB.setChecked(false);
                windRB.setChecked(false);
                cloudsRB.setChecked(false);
                test();
            }
        });

        windRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                windRB.setChecked(true);
                pressureRB.setChecked(false);
                tempRB.setChecked(false);
                cloudsRB.setChecked(false);
                test();
            }
        });

        cloudsRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cloudsRB.setChecked(true);
                pressureRB.setChecked(false);
                windRB.setChecked(false);
                tempRB.setChecked(false);
                test();
            }
        });

        test();
    }

    public void init(float max, float min) {
        chart1.setBackgroundColor(Color.WHITE);
        chart1.getDescription().setEnabled(false);
        chart1.getAxisRight().setEnabled(false);
        YAxis yAxis = chart1.getAxisLeft();
        yAxis.setAxisMaximum(max);
        yAxis.setAxisMinimum(min);
        XAxis xAxis = chart1.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    }

    public void getData() {
        try {
            URL PogodynkaEndPoint = new URL("https://api.openweathermap.org/data/2.5/forecast?zip=44-100,PL&appid=6bda4862dcc638f53d7a4758b0c6efab&lang=pl&units=metric");
            HttpsURLConnection PogodynkaConnection = (HttpsURLConnection) PogodynkaEndPoint.openConnection();
            PogodynkaConnection.setRequestProperty("Accept", "application/json");
            if (PogodynkaConnection.getResponseCode() == 200) {
                InputStreamReader is = new InputStreamReader(PogodynkaConnection.getInputStream());
                Gson gson = new Gson();
                forecast = gson.fromJson(is, Forecast.class);
                PogodynkaConnection.disconnect();
                System.out.println(forecast); //display
                list = forecast.getList();
                System.out.println(list[0].getClouds());
                System.out.println(list[1].getClouds());
                System.out.println(list[2].getClouds());
                System.out.println(list[3].getClouds());
            } else {
                //error
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void test() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData();
                    }
                });

            }
        });
    }

    public void setTemperature(){
        maxToInit = 0;
        minToInit = 0;

        ArrayList<Entry> temperatura = new ArrayList<>();
        ArrayList<Long> dates = new ArrayList<>();
        Float [] temps = new Float[list.length];

        int i = 0;
        for(List x: list){
            Float temp = Float.parseFloat(x.getMain().getTemp());
            temps[i] = temp;
            Entry entry = new Entry((float)i, temp);
            temperatura.add(entry);
            dates.add(Long.parseLong(x.getDt()));
            i++;
        }

        Arrays.sort(temps);

        maxToInit = temps[temps.length-1] + 10;
        minToInit = temps[0] - 5;

        init(maxToInit, minToInit);

        XAxis xAxis = chart1.getXAxis();
        xAxis.setValueFormatter(new DateXAxisValueFormatter(dates));

        LineDataSet set1;
        set1 = new LineDataSet(temperatura, "temperatura");
        set1.setLineWidth(3f);
        set1.setDrawCircles(false);
        set1.setColor(Color.RED);
        set1.setDrawValues(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        LineData data = new LineData(dataSets);
        chart1.setData(data);
        chart1.invalidate();
    }

    public void setPressure(){
        ArrayList<Entry> cisnienie = new ArrayList<>();
        ArrayList<Long> dates = new ArrayList<>();
        Float [] press = new Float[list.length];

        int i = 0;
        for(List x: list){
            Float pressure = Float.parseFloat(x.getMain().getPressure());
            press[i] = pressure;
            Entry entry = new Entry((float)i, pressure);
            cisnienie.add(entry);
            dates.add(Long.parseLong(x.getDt()));
            i++;
        }

        Arrays.sort(press);

        maxToInit = press[press.length -1 ] + 15;
        minToInit = press[0] - 10;

        init(maxToInit,  minToInit);

        XAxis xAxis = chart1.getXAxis();
        xAxis.setValueFormatter(new DateXAxisValueFormatter(dates));

        LineDataSet set2;
        set2 = new LineDataSet(cisnienie, "ciśnienie");
        set2.setLineWidth(2f);
        set2.setDrawCircles(false);
        set2.setColor(Color.GREEN);
        set2.setDrawValues(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set2);
        LineData data = new LineData(dataSets);
        chart1.setData(data);
        chart1.invalidate();
    }

    public void setWind(){
        ArrayList<Entry> wiatr = new ArrayList<>();
        ArrayList<Long> dates = new ArrayList<>();
        Float [] wi = new Float[list.length];

        int i = 0;
        for(List x: list){
            Float wind = Float.parseFloat(x.getWind().getSpeed());
            wi[i] = wind;
            Entry entry = new Entry((float)i, wind);
            wiatr.add(entry);
            dates.add(Long.parseLong(x.getDt()));
            i++;
        }

        Arrays.sort(wi);

        maxToInit = wi[wi.length - 1] + 5;
        minToInit = wi[0] - 5;
        if (minToInit < 0) minToInit = 0;

        init(maxToInit,  minToInit);

        XAxis xAxis = chart1.getXAxis();
        xAxis.setValueFormatter(new DateXAxisValueFormatter(dates));

        LineDataSet set2;
        set2 = new LineDataSet(wiatr, "wiatr");
        set2.setLineWidth(1f);
        set2.setDrawCircles(false);
        set2.setColor(Color.BLUE);
        set2.setDrawValues(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set2);
        LineData data = new LineData(dataSets);
        chart1.setData(data);
        chart1.invalidate();
    }

    public void setClouds(){
        ArrayList<Entry> zachmurzenie = new ArrayList<>();
        ArrayList<Long> dates = new ArrayList<>();
        Float [] cloudy = new Float[list.length];

        int i = 0;
        for(List x: list){
            Float clouds = Float.parseFloat(x.getClouds().getAll());
            cloudy[i] = clouds;
            Entry entry = new Entry((float)i, clouds);
            zachmurzenie.add(entry);
            dates.add(Long.parseLong(x.getDt()));
            i++;
        }

        Arrays.sort(cloudy);

        maxToInit = cloudy[cloudy.length - 1] + 5;
        if(maxToInit > 100) maxToInit = 100;
        minToInit = cloudy[0] - 5;
        if (minToInit < 0) minToInit = 0;

        init(maxToInit,  minToInit);

        XAxis xAxis = chart1.getXAxis();
        xAxis.setValueFormatter(new DateXAxisValueFormatter(dates));

        LineDataSet set2;
        set2 = new LineDataSet(zachmurzenie, "Zachmurzenie");
        set2.setLineWidth(1.5f);
        set2.setDrawCircles(false);
        set2.setColor(Color.CYAN);
        set2.setDrawValues(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set2);
        LineData data = new LineData(dataSets);
        chart1.setData(data);
        chart1.invalidate(); //update
    }

    public void setData(){
        if(tempRB.isChecked()){
            setTemperature();
        } else if(pressureRB.isChecked()){
            setPressure();
        }
        else if(windRB.isChecked()){
            setWind();
        } else if (cloudsRB.isChecked()){
            setClouds();
        }
    }
}
