package com.colombopizza.sasmitha.colombopizzachef.services;

import android.content.Intent;
import android.util.Log;

import com.colombopizza.sasmitha.colombopizzachef.MainActivity;
import com.colombopizza.sasmitha.colombopizzachef.OrdersActivity;
import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationReceivedResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

public class OneSignalNotificationReceivedHandlerService extends NotificationExtenderService {
    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult notification) {

        JSONObject data = notification.payload.additionalData;
        String orderID,order,notes;

        if (data != null) {
            orderID = data.optString("order_id", null);
            order = data.optString("order", null);
            notes = data.optString("notes", null);
            if (orderID != null)
                Log.i("OneSignalExample", "orderID set with value: " + orderID);

            Intent intent = new Intent(getApplicationContext(), OrdersActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("id", orderID);
            intent.putExtra("order", order);
            intent.putExtra("notes", notes);
            startActivity(intent);

        }

        return true;
    }
//    @Override
//    public void notificationReceived(OSNotification notification) {
//
//        JSONObject data = notification.payload.additionalData;
//        String customKey;
//
//        if (data != null) {
//            customKey = data.optString("id", null);
//            if (customKey != null)
//                Log.i("OneSignalExample", "customkey set with value: " + customKey);
//        }
//
//    }
}
