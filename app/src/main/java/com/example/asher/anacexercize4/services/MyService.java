package com.example.asher.anacexercize4.services;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.asher.anacexercize4.SingleActivity;
import com.example.asher.anacexercize4.fragments.ServicesFragment;
import com.example.asher.anacexercize4.interfaces.IServiceFragmentInteractionListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyService extends Service {
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
        executor.execute(new MyRunnable());
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

    public class MyRunnable implements Runnable{

        public MyRunnable(){
        }

        @Override
        public void run() {
            for(int i = 0; i<10; i++){
                if(isDestroyed)
                    return;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                intent.putExtra(MyService.SERVICE_PROGRESS_EXTRA, i);
                try {
                    _pendingIntent.send(MyService.this, CODE_SERVICE_RUNNING, intent);
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
            }

            Intent intent = new Intent();
            intent.putExtra(ON_DESTROY_EXTRA, 321);
            try {
                _pendingIntent.send(MyService.this, CODE_SERVICE_FINISHED, intent);
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }
    }
}
