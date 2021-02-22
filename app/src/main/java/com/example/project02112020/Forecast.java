package com.example.project02112020;


public class Forecast {
    private City city;

    private String cnt;

    private String cod;

    private String message;

    private List[] list;

    public City getCity ()
    {
        return city;
    }

    public void setCity (City city)
    {
        this.city = city;
    }

    public String getCnt ()
    {
        return cnt;
    }

    public void setCnt (String cnt)
    {
        this.cnt = cnt;
    }

    public String getCod ()
    {
        return cod;
    }

    public void setCod (String cod)
    {
        this.cod = cod;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public List[] getList ()
    {
        return list;
    }

    public void setList (List[] list)
    {
        this.list = list;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [city = "+city+", cnt = "+cnt+", cod = "+cod+", message = "+message+", list = "+list+"]";
    }
}
