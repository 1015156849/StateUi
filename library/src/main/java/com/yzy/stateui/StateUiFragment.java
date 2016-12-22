package com.yzy.stateui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by 杨振宇
 * github地址：https://github.com/1015156849
 * on 2016/12/21.
 */
public class StateUiFragment extends Fragment {

    private int layoutId;
    private View stateLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        stateLayout = inflater.inflate(layoutId, container, false);

        //必须设置背景颜色，因为原本xml默认是无背景色
        stateLayout.setBackgroundColor(getResources().getColor(R.color.background));

        //切换不同的Fragment的时候，设置为 不会被软键盘把布局顶上去
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //隐藏软键盘
        HideInput();

        return stateLayout;
    }

    public StateUiFragment() {
    }

    public StateUiFragment(int State) {
        layoutId = State;
    }

    public View getStateLayout() {
        return stateLayout;
    }


    /**
     * 隐藏软键盘
     */
    public void HideInput(){
        Observable.create(new Observable.OnSubscribe<Activity>() {
            @Override
            public void call(Subscriber<? super Activity> subscriber) {
                boolean ifFor = true;
                while (ifFor) {
                    if (getActivity() != null) {
                        ifFor = false;
                        subscriber.onNext(getActivity());
                        subscriber.onCompleted();
                    }
                }
            }
        }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation()).subscribe(new Observer<Activity>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {e.printStackTrace();}

            @Override
            public void onNext(Activity activity) {
                ((InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            }
        });
    }

}
