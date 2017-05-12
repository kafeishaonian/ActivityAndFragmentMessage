package com.zhihu.client.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.zhihu.client.BaseActivity;
import com.zhihu.client.utils.Functions;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
public abstract class BaseFragment extends Fragment {

    protected BaseActivity mBaseActivity;

    protected Functions mFunctions;

    /**
     * activity调试此方法进行设置Functions
     * @param functions
     */
    public void setFunctions(Functions functions){
        this.mFunctions = functions;
    }

    public void setFunctionsForFragment(){}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseActivity){
            mBaseActivity = (BaseActivity) activity;
            mBaseActivity.setFunctionsForFragment(getId());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
