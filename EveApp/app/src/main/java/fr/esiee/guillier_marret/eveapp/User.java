package fr.esiee.guillier_marret.eveapp;

import com.orm.SugarRecord;


/**
 * Created by PC-i5 on 28/02/2017.
 */

public class User extends SugarRecord {

    private String firstName;
    private String lastName;
    private boolean isContributor;
    private String email;
    private String password;


    public User(){

    }

    public User(String fn, String ln, boolean ic, String m, String pwd) {
        this.firstName = fn;
        this.lastName = ln;
        this.isContributor = ic;
        this.email = m;
        this.password = pwd;
    }

    public void setFirstName(String fn)
    {
        this.firstName = fn;
    }
    public String getFirstName()
    {
        return this.firstName;
    }

    public void setLastName(String ln)
    {
        this.lastName = ln;
    }
    public String getLastName()
    {
        return this.lastName;
    }

    public void setContributor(boolean b)
    {
        this.isContributor = b;
    }
    public boolean getContributor()
    {
        return this.isContributor;
    }

    public void setEmail(String m)
    {
        this.email = m;
    }
    public String getEmail()
    {
        return this.email;
    }

    public void setPassword(String m)
    {
        this.password = m;
    }
    public String getPassword()
    {
        return this.password;
    }

}