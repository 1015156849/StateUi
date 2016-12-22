package com.yzy.demo.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yzy.demo.R;
import com.yzy.demo.Constants.myStateUi;
import com.yzy.stateui.BaseUIFragment;

import java.util.ArrayList;

/**
 * Created by 杨振宇
 * github地址：https://github.com/1015156849
 * on 2016/12/21.
 */
public class oneFragment extends BaseUIFragment {


    public oneFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_one, container, false);
        Demo();
        return view;
    }

    /**演示代码*/
    private void Demo() {
        /**显示StateUi（注：该方法中默认先执行了 一次 HideStateUi()）*/
        ShowStateUi(R.id.containerView, myStateUi.LoadView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /**显示StateUi（注：该方法中默认先执行了 一次 HideStateUi()）*/
                ShowStateUi(R.id.containerView, myStateUi.NoNetView);
                 /**设置点击事件 （注：必须使用ShowStateUi()显示 该状态布局 后才能对该布局上的控件设置监听）*/
                setOnClick(myStateUi.NoNetView, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.NoNetView:
                                Toast.makeText(getActivity(), "点击了图片", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.Button:
                                Log.e(TAG, "点击了按钮");
                                ShowStateUi(R.id.containerView, myStateUi.LoadView);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ShowStateUi(R.id.containerView, myStateUi.NoNetView);
                                    }
                                }, 3000);
                                break;
                        }

                    }
                }, new ArrayList<Integer>() {{
                    add(R.id.NoNetView);
                    add(R.id.Button);
                }});

            }
        }, 3000);
    }

}
