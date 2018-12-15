package com.example.asher.anacexercize4.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asher.anacexercize4.R;
import com.example.asher.anacexercize4.async_util.MyAsyncTask;
import com.example.asher.anacexercize4.async_util.MyRunnableForHandler;
import com.example.asher.anacexercize4.interfaces.IAsyncTaskEvents;
import com.example.asher.anacexercize4.interfaces.IFragmentInteractionListener;

public class AsyncCounterFragment extends Fragment implements View.OnClickListener, IAsyncTaskEvents {
    public static final int ASYNC_TYPE_ASYNCTASK = 1;
    public static final int ASYNC_TYPE_THREAD = 2;
    public static final String ASYNC_TYPE_PARAM_NAME = "async_type_param";
    private int _current_async_type;

    private IFragmentInteractionListener _listener;

    Button btn_async_create, btn_async_start, btn_async_cancel;
    TextView tv_async_counter;

    private MyAsyncTask asyncTask;
    private Handler handler;

    public static AsyncCounterFragment newInstance(IFragmentInteractionListener listener, int asyncType) {
        AsyncCounterFragment fragment = new AsyncCounterFragment();
        fragment._listener = listener;
        fragment._current_async_type = asyncType;
        Bundle args = new Bundle();
        args.putInt(ASYNC_TYPE_PARAM_NAME, asyncType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            _current_async_type = getArguments().getInt(ASYNC_TYPE_PARAM_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.async_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_async_create = view.findViewById(R.id.btn_async_create);
        btn_async_create.setOnClickListener(this);
        btn_async_start = view.findViewById(R.id.btn_async_start);
        btn_async_start.setOnClickListener(this);
        btn_async_cancel = view.findViewById(R.id.btn_async_cancel);
        btn_async_cancel.setOnClickListener(this);
        tv_async_counter = view.findViewById(R.id.tv_async_counter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_async_create:
                tv_async_counter.setText("0");
                switch (_current_async_type) {
                    case ASYNC_TYPE_ASYNCTASK:
                        if(asyncTask != null){
                            if(asyncTask.getStatus() == AsyncTask.Status.RUNNING)
                                asyncTask.cancel(true);
                            asyncTask = null;
                        }
                        asyncTask = new MyAsyncTask(this);
                        ShowToast("asyncTask created");
                        break;
                    case ASYNC_TYPE_THREAD:
                        if(handler != null){
                            handler.removeMessages(0);
                            handler = null;
                        }
                        HandlerThread handlerThread = new HandlerThread("worker");
                        handlerThread.start();
                        Looper looper = handlerThread.getLooper();
                        handler = new Handler(looper);
                        ShowToast("HandlerThread created");
                        break;
                    default:
                        ShowToast("async type unset");
                        break;
                }
                break;
            case R.id.btn_async_start:
                switch (_current_async_type) {
                    case ASYNC_TYPE_ASYNCTASK:
                        if (asyncTask != null) {
                            switch (asyncTask.getStatus()) {
                                case RUNNING:
                                    if(asyncTask.isCancelled())
                                        ShowToast("asyncTask canceled, recreate");
                                    else ShowToast("already running");
                                    break;
                                case PENDING:
                                    asyncTask.execute();
                                    break;
                                case FINISHED:
                                    ShowToast("asyncTask finished, must recreate");
                                    break;
                            }
                        }
                        else ShowToast("asyncTask null");
                        break;
                    case ASYNC_TYPE_THREAD:
                        if(handler != null){
                            handler.post(MyRunnableForHandler.NewInstance(this, Looper.getMainLooper()));
                        }
                        else ShowToast("HandlerThread null");
                        break;
                    default:
                        ShowToast("async type unset");
                        break;
                }
                break;
            case R.id.btn_async_cancel:
                switch (_current_async_type) {
                    case ASYNC_TYPE_ASYNCTASK:
                        if (asyncTask != null) {
                            if (asyncTask.getStatus() == AsyncTask.Status.RUNNING)
                                asyncTask.cancel(true);
                            else ShowToast("asyncTask not running");
                        } else ShowToast("asyncTask null");
                        break;
                    case ASYNC_TYPE_THREAD:
                        if(handler != null){
                            handler.removeMessages(0);
                        }
                        else ShowToast("HandlerThread null");
                        break;
                    default:
                        ShowToast("async type unset");
                        break;
                }
                break;
        }
    }

    private void ShowToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPreExecute() {
        String message = "";
        switch (_current_async_type) {
            case ASYNC_TYPE_ASYNCTASK:
                message = "asyncTask";
                break;
            case ASYNC_TYPE_THREAD:
                message = "threadHandler";
                break;
            default:
                ShowToast("async type not set");
                return;
        }
        ShowToast("preparing " + message);
    }

    @Override
    public void onPostExecute() {
        UpdateTvCounter(R.string.done_message);
    }

    @Override
    public void onProgressUpdate(Integer integer) {
        UpdateTvCounter(integer.toString());
    }

    @Override
    public void onCancelled() {
        UpdateTvCounter(R.string.cancelled_message);
    }

    private void UpdateTvCounter(String message) {
        if (tv_async_counter != null)
            tv_async_counter.setText(message);
    }

    private void UpdateTvCounter(int stringId) {
        if (tv_async_counter != null)
            tv_async_counter.setText(stringId);
    }
}
