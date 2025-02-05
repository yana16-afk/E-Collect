package com.application.ecollectapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "UserSessionPreferences";
    private static final String KEY_USER_ID = "UserID";
    private final SharedPreferences userSessionPreferences;
    private final SharedPreferences.Editor userSessionEditor;
    private final Context appContext;

    public SessionManager(Context context) {
        this.appContext = context;
        userSessionPreferences = appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        userSessionEditor = userSessionPreferences.edit();
    }

    public void saveUserId(String userId) {
        userSessionEditor.putString(KEY_USER_ID, userId);
        userSessionEditor.commit();
    }

    public String getUserId() {
        return userSessionPreferences.getString(KEY_USER_ID, null);
    }

    public void clearSession() {
        userSessionEditor.clear();
        userSessionEditor.commit();
    }
}

//Use to connect activities to database according to user

//        SessionManager sessionManager = new SessionManager(this);
//        String userId = sessionManager.getUserId();
