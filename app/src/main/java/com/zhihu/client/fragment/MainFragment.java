package com.zhihu.client.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhihu.client.BaseActivity;
import com.zhihu.client.MainActivity;
import com.zhihu.client.R;
import com.zhihu.client.utils.DataSynEvent;
import com.zhihu.client.utils.FunctionException;
import com.zhihu.client.utils.Functions;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
public class MainFragment extends BaseFragment {

    private static final String TAG = MainFragment.class.getSimpleName();
    public static MainFragment newInstance(){
        return new MainFragment();
    }

    /**
     * 没有参数没有返回值的函数
     */
    public static final String NO_PARAM_NO_RESULT = "NO_PARAM_NO_RESULT";
    /**
     * 没有参数有返回值的函数
     */
    public static final String FUNCTION_NO_PARAM_HAS_RESULT = "FUNCTION_NO_PARAM_HAS_RESULT";
    /**
     * 有参数没有返回值的函数
     */
    public static final String FUNCTION_HAS_PARAM_NO_RESULT = "FUNCTION_HAS_PARAM_NO_RESULT";
    /**
     * 有参数有返回值的函数
     */
    public static final String EVENT_HAS_PARAM_HAS_RESULT = "EVENT_HAS_PARAM_HAS_RESULT";

    /**
     * 具有多个参数的函数
     */
    public static final String FUNCTION_HAS_MORE_PARAM = "FUNCTION_HAS_MORE_PARAM";

    /**
     * 具有多个参数的函数
     */
    public static final String FUNCTION_HAS_MORE_PARAM_Bundle = "FUNCTION_HAS_MORE_PARAM_Bundle";

    private Button mBut1, mBut2, mBut3, mBut4;
    private TextView mResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        EventBus.getDefault().register(this);
        initView();
        initListener();
    }

    private void initView(){
        mBut1 = (Button) getView().findViewById(R.id.click1);
        mBut2 = (Button) getView().findViewById(R.id.click2);
        mBut3 = (Button) getView().findViewById(R.id.click3);
        mBut4 = (Button) getView().findViewById(R.id.click4);
        mResult = (TextView) getView().findViewById(R.id.result);
    }


    private void initListener(){
        mBut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", System.currentTimeMillis() + "啦啦啦啦");
//                EventBus.getDefault().post(new DataSynEvent());

                try {
                    mFunctions.invokeFunc(NO_PARAM_NO_RESULT);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        });
        mBut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = null;
                try {
                    result = mFunctions.invokeFuncWithResult(FUNCTION_NO_PARAM_HAS_RESULT, String.class);
                    Log.e(TAG, result);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
                mResult.setText(result);
            }
        });
        mBut3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mFunctions.invokeFunc(FUNCTION_HAS_PARAM_NO_RESULT, 100);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        });
        mBut4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> re = null;
                try {
                    re = mFunctions.invokeFuncWithResult(EVENT_HAS_PARAM_HAS_RESULT, 100, List.class);
                    Log.e(TAG, "啦啦啦" + re.get(0) + "啦啦啦" + re.get(1) + "啦啦啦" + re.get(2));
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
                if (re != null) {
                    String st = "";
                    for (int i = 0; i < re.size(); i++) {
                        st += re.get(i)+" ";
                    }
                    mResult.setText(st);
                }
            }
        });

        getView().findViewById(R.id.click5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mFunctions.invokeFunc(FUNCTION_HAS_MORE_PARAM, new Functions.FunctionParams.FunctionParamsBuilder().putString("你好")
                            .putString("我是fragment").putInt(200).create());
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        });

        getView().findViewById(R.id.click6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Bundle b = new Bundle();
                    b.putString("p", "你好activity");
                    b.putString("p1", "我是fragment");
                    b.putInt("p2", 200);
                    mFunctions.invokeFunc(FUNCTION_HAS_MORE_PARAM_Bundle, b);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void setFunctionsForFragment() {
        super.setFunctionsForFragment();
        BaseActivity activity = (BaseActivity) getActivity();
        activity.setFunc(new Functions().addFunction(new Functions.FunctionNoParamAndResult(MainActivity.NO) {
            @Override
            public void function() {
                Toast.makeText(getActivity(), "Activity调用没有返回值没有参数的数据", Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
