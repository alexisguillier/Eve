package fr.esiee.guillier_marret.eveapp;

/**
 * Created by PC-i5 on 03/03/2017.
 */


import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by PC-i5 on 01/03/2017.
 */

public class Participation extends SugarRecord {

    private User user;
    private Event event;
    private String submissionDate;
    private boolean payment;

    public Participation(){

    }

    public Participation(User u, Event e){
        this.user = u;
        this.event = e;
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        this.submissionDate = new StringBuilder().append(day).append("/").append(month).append("/").append(year).toString();
        this.payment = false;
    }
    public void setUser(User id){
        this.user = id;
    }
    public User getUser(){
        return this.user;
    }
    public void setEvent(Event id){
        this.event = id;
    }
    public Event getEvent(){
        return this.event;
    }
    public void setSubmissionDate(String d){
        this.submissionDate = d;
    }
    public String getSubmissionDate(){
        return this.submissionDate;
    }
    public void setPayment(boolean b){
        this.payment = b;
    }
    public boolean getPayment(){
        return this.payment;
    }
}