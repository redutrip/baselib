package com.keke.baselib.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keke.baselib.utils.BaseLog;
import com.trello.rxlifecycle.components.RxFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;


public  abstract class BaseFragment extends RxFragment implements BaseView{

    protected Subscription subscription;
    protected List<Subscription> subscriptionList = new ArrayList<>();
    private boolean isVisible = false;//懒加载判断 当前Fragment是否可见
    private boolean hasLoad = false;//懒加载判断 是否是第一次加载数据
    private View convertView;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        BaseLog.i( "Fragment onAttach: "+ getClass().getSimpleName());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseLog.i("Fragment onCreate: "+ getClass().getSimpleName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BaseLog.i("Fragment onCreateView: " + getClass().getSimpleName());
        if(convertView == null ){
            convertView = inflater.inflate(setLayoutId(), container, false);
            unbinder = ButterKnife.bind(this, convertView);
            createView(savedInstanceState);
            initView();
            lazyloadData();
        }

        ViewGroup parent = (ViewGroup) convertView.getParent();
        if (parent != null) {
            parent.removeView(convertView);
        }

        return convertView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BaseLog.i("Fragment onActivityCreated: "  + getClass().getSimpleName());
    }

    @Override
    public void onStart() {
        super.onStart();
        BaseLog.i("Fragment onStart: "  +  getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        BaseLog.i("Fragment onPause: "   + getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
        BaseLog.i("Fragment onResume: "   + getClass().getSimpleName());
    }

    @Override
    public void onStop() {
        super.onStop();
        BaseLog.i("Fragment onStop: "   + getClass().getSimpleName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseLog.i("Fragment onDestroy: "   + getClass().getSimpleName());
    }




    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        BaseLog.i("isVisibleToUser " + isVisibleToUser + "   " + this.getClass().getSimpleName());
        if (isVisibleToUser) {
            isVisible = true;
            lazyloadData();
        } else {
            isVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    //	数据懒加载
    private void lazyloadData() {
        if ( isVisible && ! hasLoad) {
            loadData();
            hasLoad = true;
            return;
        }
    }



    /**
     * 加载页面布局文件
     * @return
     */
    protected abstract int setLayoutId();


    protected abstract void createView( Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract void loadData();


    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        if(subscriptionList.size()!=0){
            for (Subscription subscription : subscriptionList){
                if (subscription != null && !subscription.isUnsubscribed()) {
                    subscription.unsubscribe();
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
        if(unbinder != null)
            unbinder.unbind();
        BaseLog.i("Fragment onDestroyView: "   + getClass().getSimpleName());
    }

    @Override
    public void showLoadingBar() {
        ((BaseActivity)getActivity()).getProgressMaterial().show();
    }

    @Override
    public void hideLoadindBar() {
        ((BaseActivity)getActivity()).getProgressMaterial().hide();
    }

    public void showWaringDialog(String msg){
        ((BaseActivity)getActivity()).showWaringDialog(msg);
    }

    public void showErrorDialog(String msg){
        ((BaseActivity)getActivity()).showErrorDialog(msg);
    }

    public void showSuccessDialog(String msg){
        ((BaseActivity)getActivity()).showSuccessDialog(msg);
    }
}
