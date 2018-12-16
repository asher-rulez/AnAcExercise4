package com.example.asher.anacexercize4.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.example.asher.anacexercize4.hard_work_imitation.MyRunnable;
import com.example.asher.anacexercize4.interfaces.ICheckParentService;
import com.example.asher.anacexercize4.receivers.MyReceiver;

import static com.example.asher.anacexercize4.fragments.ServicesFragment.SERVICE_TYPE_INTENTSERVICE;
import static com.example.asher.anacexercize4.fragments.ServicesFragment.SERVICE_TYPE_SERVICE;

public class MyIntentService extends IntentService implements ICheckParentService {
    public static final String UPDATE_TYPE_EXTRA = "UPDATE_TYPE_EXTRA";
    public static final String INTENT_EXTRA = "INTENT_EXTRA";

    private static boolean isDestroyed;

    public MyIntentService() {
        super("MyIntentService");
    }

    public static void startServiceInstance(Context context) {
        Intent intent = new Intent(context, MyIntentService.class);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            isDestroyed = false;
            handleWork();
        }
    }

    private void handleWork() {
        new MyRunnable(this, SERVICE_TYPE_INTENTSERVICE).run();
    }

    @Override
    public boolean checkIsDestroyed() {
        return isDestroyed;
    }

    @Override
    public void send(Context context, int code, Intent intent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void sendMyBroadcast(int code, Intent intent) {
        Intent intentToSend = new Intent();
        intentToSend.setAction(MyReceiver.MY_BROADCAST_RECEIVER_ACTION);
        intentToSend.putExtra(UPDATE_TYPE_EXTRA, code);
        intentToSend.putExtra(INTENT_EXTRA, intent);
        sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        isDestroyed = true;
        super.onDestroy();
    }
}
