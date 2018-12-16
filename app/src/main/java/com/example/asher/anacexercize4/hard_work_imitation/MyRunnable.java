package com.example.asher.anacexercize4.hard_work_imitation;

import android.app.PendingIntent;
import android.content.Intent;

import com.example.asher.anacexercize4.interfaces.ICheckParentService;
import com.example.asher.anacexercize4.services.MyService;

import static com.example.asher.anacexercize4.fragments.ServicesFragment.SERVICE_TYPE_INTENTSERVICE;
import static com.example.asher.anacexercize4.fragments.ServicesFragment.SERVICE_TYPE_SERVICE;
import static com.example.asher.anacexercize4.services.MyService.CODE_SERVICE_FINISHED;
import static com.example.asher.anacexercize4.services.MyService.CODE_SERVICE_RUNNING;
import static com.example.asher.anacexercize4.services.MyService.ON_DESTROY_EXTRA;

public class MyRunnable implements Runnable {
    private int serviceTypeId;
    private ICheckParentService parentService;

    public MyRunnable(ICheckParentService _parentService, int _serviceTypeId) {
        parentService = _parentService;
        serviceTypeId = _serviceTypeId;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (isDestroyed())
                return;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent();
            intent.putExtra(MyService.SERVICE_PROGRESS_EXTRA, i);
            Send(CODE_SERVICE_RUNNING, intent);
        }

        Intent intent = new Intent();
        intent.putExtra(ON_DESTROY_EXTRA, 321);
        Send(CODE_SERVICE_FINISHED, intent);
    }

    private boolean isDestroyed() {
        return parentService.checkIsDestroyed();
    }

    private void Send(int code, Intent intent){
        switch (serviceTypeId){
            case SERVICE_TYPE_SERVICE:
                parentService.send(parentService.getContext(), code, intent);
                break;
            case SERVICE_TYPE_INTENTSERVICE:
                parentService.sendMyBroadcast(code, intent);
                break;
        }
    }
}
