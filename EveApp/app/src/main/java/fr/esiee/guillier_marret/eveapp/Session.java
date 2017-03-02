package fr.esiee.guillier_marret.eveapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by PC-i5 on 02/03/2017.
 */

public class Session {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    private static final String PREF_NAME = "SessionPref";
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String KEY_ID = "userID";
    private static final String KEY_FIRSTNAME = "FirstName";
    private static final String KEY_SECONDNAME = "SecondName";

    public Session(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, 0);
        editor = pref.edit();
    }

    public void createSession(Long id, String firstName, String secondName){
        editor.putBoolean(IS_LOGIN, true);
        editor.putLong(KEY_ID, id);
        editor.putString(KEY_FIRSTNAME, firstName);
        editor.putString(KEY_SECONDNAME, secondName);
        editor.commit();
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public User getUser(){
        if(this.isLoggedIn()){
            return User.findById(User.class, pref.getLong(KEY_ID, 0));
        }
        return null;
    }

}