package com.zhihu.client;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.zhihu.client.utils.Functions;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
public abstract class BaseActivity extends FragmentActivity {
    /**
     * 为fragment设置function，具体实现子类来做
     * @param fragmentid
     */
    public void setFunctionsForFragment(int fragmentid){};

    protected Functions functions;




    /**
     * activity调试此方法进行设置Functions
     * @param functions
     */
    public void setFunc(Functions functions){
        this.functions = functions;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
