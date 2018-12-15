package com.example.asher.anacexercize4.async_util;

import com.example.asher.anacexercize4.interfaces.IAsyncTaskEvents;

public class MyRunnableForUI implements Runnable {
    public static final int UPDATE_TYPE_PROGRESS = 1;
    public static final int UPDATE_TYPE_CANCELLED = 2;
    public static final int UPDATE_TYPE_DONE = 3;

    private IAsyncTaskEvents listener;
    private Object message;
    private int updateType;

    public static MyRunnableForUI NewInstance(IAsyncTaskEvents _listener, int _updateType, Object _message){
        MyRunnableForUI runnable = new MyRunnableForUI();
        runnable.listener = _listener;
        runnable.message = _message;
        runnable.updateType = _updateType;
        return runnable;
    }

    @Override
    public void run() {
        switch (updateType){
            case UPDATE_TYPE_PROGRESS:
                listener.onProgressUpdate((Integer)message);
                break;
            case UPDATE_TYPE_CANCELLED:
                listener.onCancelled();
                break;
            case UPDATE_TYPE_DONE:
                listener.onPostExecute();
                break;
        }
    }
}
