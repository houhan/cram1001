package com.example.user.cram1001.GCM;

/**
 * Created by user on 2016/10/9.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

/**
 * Created by Justin on 2016/4/15.
 */
public class GCMUtils {

    private static final String TAG = "GCMUtils";
    private TextView regId;

    public static String getGCMToken(Context context) {
        try {
            // [START register_for_gcm]
            // Initially this call goes out to the network to retrieve the token, subsequent calls
            // are local.
            // R.string.gcm_defaultSenderId (the Sender ID) is typically derived from google-services.json.
            // See https://developers.google.com/cloud-messaging/android/start for details on this file.
            // [START get_token]
            InstanceID instanceID = InstanceID.getInstance(context);
            String token = instanceID.getToken(Constant.SENDER_ID, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            // [END get_token]
            Log.i(TAG, "GCM Registration Token: " + token);
            // [END register_for_gcm]

            return token;
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
        }

        return null;
    }

    public static void saveToken(Context context, String token) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.commit();
    }

    public static String getSavedToken(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString("token", null);
    }
}