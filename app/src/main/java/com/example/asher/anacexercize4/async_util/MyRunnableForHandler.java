package com.example.asher.anacexercize4.async_util;

import android.os.Handler;
import android.os.Looper;

import com.example.asher.anacexercize4.interfaces.IAsyncTaskEvents;

public class MyRunnableForHandler implements Runnable {

    IAsyncTaskEvents listener;
    Handler handler;
    Object lock = new Object();

    public static MyRunnableForHandler NewInstance(IAsyncTaskEvents _listener, Looper _looper){
        MyRunnableForHandler runnable = new MyRunnableForHandler();
        runnable.setHandler(new Handler(_looper));
        runnable.setListener(_listener);
        return runnable;
    }

    public void setListener(IAsyncTaskEvents listener) {
        this.listener = listener;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        for(int i = 1; i <= 10; i++){
            handler.post(MyRunnableForUI.NewInstance(listener, MyRunnableForUI.UPDATE_TYPE_PROGRESS, i));
            synchronized (lock){
                try {
                    lock.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        handler.post(MyRunnableForUI.NewInstance(listener, MyRunnableForUI.UPDATE_TYPE_DONE, null));
    }
}
