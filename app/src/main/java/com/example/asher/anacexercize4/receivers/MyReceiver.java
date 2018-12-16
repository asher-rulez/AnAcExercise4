package com.example.asher.anacexercize4.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.asher.anacexercize4.async_util.ObservableObject;

public class MyReceiver extends BroadcastReceiver {
    public static final String MY_BROADCAST_RECEIVER_ACTION = "com.example.asher.anacexercize4.receivers.myreceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        ObservableObject.getInstance().updateValue(intent);
    }
}
