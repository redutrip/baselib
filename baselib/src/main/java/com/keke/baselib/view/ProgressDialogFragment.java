package com.keke.baselib.view;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.keke.baselib.R;
import com.keke.viewlib.progress.CircleProgressBar;


public class ProgressDialogFragment extends DialogFragment {
    private Dialog builder;
    private CircleProgressBar progressBar;
    private OnFinishListener onFinishListener;
    private TextView contentTv;

    private String contentStr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new Dialog(getActivity(), R.style.dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.view_framentdialog_progress, null);

        progressBar = (CircleProgressBar) view.findViewById(R.id.frgdialog_progress);
        contentTv = (TextView) view.findViewById(R.id.frgdialog_normal_content_tv);
        contentTv.setText(contentStr);
        builder.setContentView(view);
        return builder;
    }


    public void setContentStr(String contentStr) {
        this.contentStr = contentStr;
        if(contentTv!= null )
            contentTv.setText(contentStr);
    }


    public void showDialog(FragmentTransaction ft ){
        ProgressDialogFragment.this.show(ft ,"progressDialogFragment");
        CountDownTimer timer = new CountDownTimer(1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                if(ProgressDialogFragment.this.onFinishListener !=null){
                    ProgressDialogFragment.this.onFinishListener.onFinish();
                }
                ProgressDialogFragment.this.dismiss();
            }
        };
        timer.start();
    }

    public void setOnFinishListener(OnFinishListener onFinishListener){
        this.onFinishListener = onFinishListener;
    }

    public interface OnFinishListener{
        void onFinish();
    }

}
   
