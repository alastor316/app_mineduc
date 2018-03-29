package com.example.marcelocadiz.app_mineduc;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Marxelo on 02-01-2018.
 */

public class RecibirMensaje extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e("FIREBASE", remoteMessage.getNotification().getBody());
    }
}
