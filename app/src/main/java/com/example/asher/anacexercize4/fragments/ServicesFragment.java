package com.example.asher.anacexercize4.fragments;


import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.asher.anacexercize4.R;
import com.example.asher.anacexercize4.interfaces.IServiceFragmentInteractionListener;
import com.example.asher.anacexercize4.services.MyIntentService;
import com.example.asher.anacexercize4.services.MyService;

public class ServicesFragment extends Fragment implements View.OnClickListener, IServiceFragmentInteractionListener {
    public static final int CODE_SERVICE = 1001;
    public static final String PENDING_INTENT_EXTRA = "pending_intent";
    public static final int SERVICE_TYPE_SERVICE = 3001;
    public static final int SERVICE_TYPE_INTENTSERVICE = 3002;
    public static final int LOG_TYPE_PROGRESS = 4001;

    TextView tv_service_status, tv_service_log;
    Button btn_start_service, btn_start_intent_service, btn_stop_service;

    private boolean IsIntentServiceRunning, IsServiceRunning;

    public boolean isIntentServiceRunning() {
        return IsIntentServiceRunning;
    }

    public void setIntentServiceRunning(boolean intentServiceRunning) {
        IsIntentServiceRunning = intentServiceRunning;
    }

    public boolean isServiceRunning() {
        return IsServiceRunning;
    }

    public void setServiceRunning(boolean serviceRunning) {
        IsServiceRunning = serviceRunning;
    }

    public ServicesFragment() {
    }

    public static ServicesFragment newInstance() {
        ServicesFragment fragment = new ServicesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_services, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_service_status = view.findViewById(R.id.tv_fs_service_status);
        tv_service_status.setText(R.string.no_service_running);
        tv_service_log = view.findViewById(R.id.tv_fs_service_messages);
        tv_service_log.setText(R.string.no_logs);
        btn_start_service = view.findViewById(R.id.btn_fs_start_service);
        btn_start_service.setOnClickListener(this);
        btn_start_intent_service = view.findViewById(R.id.btn_fs_start_intent_service);
        btn_start_intent_service.setOnClickListener(this);
        btn_stop_service = view.findViewById(R.id.btn_fs_stop_service);
        btn_stop_service.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fs_start_service:
                StartMyService();
                break;
            case R.id.btn_fs_start_intent_service:
                StartMyIntentService();
                break;
            case R.id.btn_fs_stop_service:
                StopService();
                StopIntentService();
                break;
        }
    }

    private void StartMyService() {

        StopIntentService();
        PendingIntent pi = getActivity().createPendingResult(CODE_SERVICE, new Intent(), 0);
        Intent intent = new Intent(getActivity(), MyService.class).putExtra(PENDING_INTENT_EXTRA, pi);
        getActivity().startService(intent);
    }

    private void StopIntentService() {
        getActivity().stopService(new Intent(getContext(), MyIntentService.class));
    }

    private void StartMyIntentService() {
        StopService();
        MyIntentService.startServiceInstance(getContext());
    }

    private void StopService() {
        getActivity().stopService(new Intent(getActivity(), MyService.class));
    }

    @Override
    public void SetStatus(int serviceTypeId, String message) {
        String statusMessage = "";
        switch (serviceTypeId){
            case SERVICE_TYPE_SERVICE:
                statusMessage = getString(R.string.service_type_service);
                break;
            case SERVICE_TYPE_INTENTSERVICE:
                statusMessage = getString(R.string.service_type_intentservice);
                break;
        }
        tv_service_status.setText(statusMessage + message);
    }

    @Override
    public void SetLog(int logTypeId, String message) {
        switch (logTypeId){
            case LOG_TYPE_PROGRESS:
                tv_service_log.setText(getString(R.string.progress) + message);
                break;
        }
    }
}
