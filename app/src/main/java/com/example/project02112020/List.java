package com.example.project02112020;

public class List {
    private String dt;

    private String pop;

    private Rain rain;

    private String visibility;

    private String dt_txt;

    private Weather[] weather;

    private Main main;

    private Clouds clouds;

    private Sys sys;

    private Wind wind;

    public String getDt ()
    {
        return dt;
    }

    public void setDt (String dt)
    {
        this.dt = dt;
    }

    public String getPop ()
    {
        return pop;
    }

    public void setPop (String pop)
    {
        this.pop = pop;
    }

    public Rain getRain ()
    {
        return rain;
    }

    public void setRain (Rain rain)
    {
        this.rain = rain;
    }

    public String getVisibility ()
    {
        return visibility;
    }

    public void setVisibility (String visibility)
    {
        this.visibility = visibility;
    }

    public String getDt_txt ()
    {
        return dt_txt;
    }

    public void setDt_txt (String dt_txt)
    {
        this.dt_txt = dt_txt;
    }

    public Weather[] getWeather ()
    {
        return weather;
    }

    public void setWeather (Weather[] weather)
    {
        this.weather = weather;
    }

    public Main getMain ()
    {
        return main;
    }

    public void setMain (Main main)
    {
        this.main = main;
    }

    public Clouds getClouds ()
    {
        return clouds;
    }

    public void setClouds (Clouds clouds)
    {
        this.clouds = clouds;
    }

    public Sys getSys ()
    {
        return sys;
    }

    public void setSys (Sys sys)
    {
        this.sys = sys;
    }

    public Wind getWind ()
    {
        return wind;
    }

    public void setWind (Wind wind)
    {
        this.wind = wind;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dt = "+dt+", pop = "+pop+", rain = "+rain+", visibility = "+visibility+", dt_txt = "+dt_txt+", weather = "+weather+", main = "+main+", clouds = "+clouds+", sys = "+sys+", wind = "+wind+"]";
    }
}
