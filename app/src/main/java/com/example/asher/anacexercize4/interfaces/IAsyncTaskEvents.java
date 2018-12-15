package com.example.asher.anacexercize4.interfaces;

public interface IAsyncTaskEvents {
    void onPreExecute();
    void onPostExecute();
    void onProgressUpdate(Integer integer);
    void onCancelled();
}
