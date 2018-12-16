package com.example.asher.anacexercize4.services;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.asher.anacexercize4.SingleActivity;
import com.example.asher.anacexercize4.fragments.ServicesFragment;
import com.example.asher.anacexercize4.hard_work_imitation.MyRunnable;
import com.example.asher.anacexercize4.interfaces.ICheckParentService;
import com.example.asher.anacexercize4.interfaces.IServiceFragmentInteractionListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.asher.anacexercize4.fragments.ServicesFragment.SERVICE_TYPE_SERVICE;

public class MyService extends Service implements ICheckParentService {
    private final String LOG_SERVICE = "service";
    public static final int CODE_SERVICE_RUNNING = 2001;
    public static final int CODE_SERVICE_FINISHED = 2002;
    public static final String SERVICE_PROGRESS_EXTRA = "SERVICE_PROGRESS_EXTRA";
    public static final String ON_DESTROY_EXTRA = "ON_DESTROY_EXTRA";
    private static boolean isDestroyed;
    private ExecutorService executor;
    private PendingIntent _pendingIntent;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        executor = Executors.newFixedThreadPool(1);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        _pendingIntent = intent.getParcelableExtra(ServicesFragment.PENDING_INTENT_EXTRA);
        isDestroyed = false;
        executor.execute(new MyRunnable(this, SERVICE_TYPE_SERVICE));
        return START_NOT_STICKY;
    }

    private void stopService(){
        Log.d(LOG_SERVICE, "service stopped");
        stopSelf();
    }

    @Override
    public void onDestroy() {
        isDestroyed = true;
        Intent intent = new Intent();
        intent.putExtra(ON_DESTROY_EXTRA, 123);
        try {
            _pendingIntent.send(this, CODE_SERVICE_FINISHED, intent);
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public boolean checkIsDestroyed() {
        return isDestroyed;
    }

    @Override
    public void send(Context context, int code, Intent intent) {
        try {
            _pendingIntent.send(context, code, intent);
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void sendMyBroadcast(int code, Intent intent) {
        throw new UnsupportedOperationException();
    }
}
