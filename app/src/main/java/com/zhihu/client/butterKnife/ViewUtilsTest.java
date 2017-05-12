package com.zhihu.client.butterKnife;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
public class ViewUtilsTest {

    public static void inject(final Activity activity){
        /**
         * 通过字节码获取activity类中所有的字段，在获取Field的时候一定要使用
         * getDeclaredFields(),
         * 因为只有该方法才能获取到任何权限修饰符的Filed，包括私有的
         */
        Class clazz = activity.getClass();
        Field[] declaredFields =  clazz.getDeclaredFields();
        //一个Activity中可能有多个Field，因此遍历
        for (int i = 0; i < declaredFields.length; i++){
            Field field = declaredFields[i];
            //设置为可访问，暴力反射，就算是私有的也能访问到
            field.setAccessible(true);
            //获取到字段上面的注解对象
            ViewInject annotation = (ViewInject)field.getAnnotation(ViewInject.class);
            //一定对annotation是否等于null进行判断，因为并不是所有Filed上都有我们想要的注解
            if (annotation == null){
                continue;
            }
            //获取注解中的值
            int id = annotation.value();
            //获取控件
            View view = activity.findViewById(id);
            try {
                //将该控件设置给field
                field.set(activity, view);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        //获取所有的方法（私有方法也可以获取到）
        Method[] declareMethods = clazz.getDeclaredMethods();
        for (int i = 0; i < declareMethods.length; i++){
            final Method method = declareMethods[i];
            //获取方法上面的注解
            OnClick annotation = (OnClick)method.getAnnotation(OnClick.class);
            if (annotation == null){
                //如果该方法上面没有注解，循环下一个
                continue;
            }
            //获取注解中的数据，因为可以给多个Button绑定点击事件，因此定义注解类时，要是用的是int[]作为数据类型
            int[] value = annotation.value();
            for (int j = 0; j < value.length; j++){
                int id = value[j];
                final View button = activity.findViewById(id);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            //反射调用用户指定的方法
                            method.invoke(activity, button);
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
       }
    }
}
