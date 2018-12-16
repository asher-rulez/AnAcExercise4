package com.example.asher.anacexercize4.interfaces;

import android.content.Context;
import android.content.Intent;

public interface ICheckParentService {
    boolean checkIsDestroyed();
    void send(Context context, int code, Intent intent);
    Context getContext();
    void sendMyBroadcast(int code, Intent intent);
}
