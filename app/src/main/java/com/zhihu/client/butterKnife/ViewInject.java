package com.zhihu.client.butterKnife;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Hongmingwei on 2017/5/10 0010.
 *
 *  @Retention 用于声明注解生效的声明周期, 有三个枚举值可以选择
 *  1 RetentionPolicy.SOURCE  注解只保留在源码上面，编译成class时候自动被编译器抹除
 *  2 RetentionPolicy.CLASS  注解只保留在字节码上，VM加载字节码时自动抹除
 *  3 RetentionPolicy.RUNTIME 注解永久保留，可以被VM加载时加载到内存中
 *  我们的目的是想在VM运行时对Filed上的注解进行反射操作，因此Retention值必须设置RUNTIME
 *
 *  @Target 用于指定该注解可以声明在那些成员上面，常见的值有FIELD和Method
 *  由于我们的当前注解类是想声明在Filed上面
 *  因此这里设置ElementType.FLELD
 *  注意：如果@Target不设置，则会默认注解到所有元素上  不推荐这样写
 *
 *  @interface 是声明注解类的组合关键字
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewInject {
    public abstract int value();
}
