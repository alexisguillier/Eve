package fr.esiee.guillier_marret.eveapp;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by PC-i5 on 01/03/2017.
 */

public class Event extends SugarRecord {

    private String name;
    private String description;
    private String location;
    private String date;
    private int maxTickets;
    private double price;
    private double priceNonContributor;

    private boolean isActive;


    public Event(){

    }

    public Event(String n, String d, int mt, double p, double pnc, String desc) {
        this.name = n;
        this.date = d;
        this.maxTickets = mt;
        this.price = p;
        this.priceNonContributor = pnc;
        this.description = desc;
        this.isActive = true;
    }

    public void setName(String n)
    {
        this.name = n;
    }
    public String getName()
    {
        return this.name;
    }

    public void setLocation(String l)
    {
        this.location = l;
    }
    public String getLocation()
    {
        return this.location;
    }

    public void setDate(String d)
    {
        this.date = d;
    }
    public String getDate()
    {
        return this.date;
    }

    public void setMaxTickets(int mt)
    {
        this.maxTickets = mt;
    }
    public int getMaxTickets()
    {
        return this.maxTickets;
    }

    public void setPrice(double p)
    {
        this.price = p;
    }
    public double getPrice()
    {
        return this.price;
    }

    public void setPriceNonContributor(double p)
    {
        this.priceNonContributor = p;
    }
    public double getPriceNonContributor()
    {
        return this.priceNonContributor;
    }

    public void setDescription(String d)
    {
        this.description = d;
    }
    public String getDescription()
    {
        return this.description;
    }

    public void setActive(boolean b)
    {
        this.isActive = b;
    }
    public boolean getActive()
    {
        return this.isActive;
    }
}