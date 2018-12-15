package com.example.asher.anacexercize4.async_util;

import android.os.AsyncTask;

import com.example.asher.anacexercize4.interfaces.IAsyncTaskEvents;

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

    IAsyncTaskEvents listener;

    Object lock = new Object();

    public MyAsyncTask(IAsyncTaskEvents _listener){
        listener = _listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for(int i = 1; i <= 10; i++){
            if(isCancelled())
                onCancelled();
            publishProgress(i);
            try {
                synchronized (lock){
                    lock.wait(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        listener.onProgressUpdate(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onPostExecute();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        listener.onCancelled();
    }
}
