package com.yzy.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.yzy.demo.Constants.myStateUi;
import com.yzy.demo.fragment.oneFragment;
import com.yzy.stateui.BaseUIActivity;
import com.yzy.stateui.BaseUIFragment;
import com.yzy.demo.adapter.ViewPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 杨振宇
 * github地址：https://github.com/1015156849
 * on 2016/12/21.
 */
public class MainActivity extends BaseUIActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager= (ViewPager) findViewById(R.id.viewPager);
        TabLayout tabLayout= (TabLayout) findViewById(R.id.tab);


        ArrayList<BaseUIFragment> list = new ArrayList<>();

        list.add(new oneFragment().setTitle("Test1"));
        list.add(new oneFragment().setTitle("Test2"));
//        list.add(new oneFragment().setTitle("Test3"));
//        list.add(new oneFragment().setTitle("Test4"));

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), list));
        viewPager.setOffscreenPageLimit(list.size());
        tabLayout.setupWithViewPager(viewPager);

        Demo();
    }

    /**演示代码*/
    private void Demo(){

        /**显示StateUi（注：该方法中默认先执行了 一次 HideStateUi()）*/
        ShowStateUi(R.id.containerView, myStateUi.LoadView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {//模拟耗时操作
                /**显示数据，调用 HideStateUi() 也可以*/
                ShowStateUi(R.id.containerView,myStateUi.ShowDatasView);
//                /**显示StateUi（注：该方法中默认先执行了 一次 HideStateUi()）*/
//                ShowStateUi(R.id.containerView, myStateUi.NoNetView);
//                /**设置点击事件 （注：必须使用ShowStateUi()显示 该状态布局 后才能对该布局上的控件设置监听）*/
//                setOnClick(myStateUi.NoNetView, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        switch (v.getId()) {
//                            case R.id.NoNetView:
//                                ShowStateUi(R.id.containerView,myStateUi.ShowDatasView);
//                                break;
//                            case R.id.Button:
//                                ShowStateUi(R.id.containerView,myStateUi.LoadView);
//                                new Handler().postDelayed(new Runnable() {//模拟耗时操作
//                                    @Override
//                                    public void run() {
//                                        ShowStateUi(R.id.containerView,myStateUi.NoNetView);
//                                    }
//                                },3000);
//                                break;
//                        }
//                    }
//                }, new ArrayList<Integer>() {{
//                    add(R.id.NoNetView);
//                    add(R.id.Button);
//                }});/**此处参数为需要设置点击监听的控件ID List*/
            }
        }, 3000);
    }
}
