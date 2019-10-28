package com.haocxx.xxasm;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.lang.reflect.Method;

public class MainActivity extends BaseActivity {

    private BaseActivity a = new BaseActivity();
    protected BaseActivity b = new BaseActivity();
    private int asd = 1;

//    private static Method mSortChildDrawingOrderMethod = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sortChildDrawingOrder();
        b = a;
        b = get();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                b = a;
                test(asd, 1);
//                test2(a, 1);

            }
        };
        new Handler().post(runnable);
//
//        try {
//            if (mSortChildDrawingOrderMethod == null) {
//                mSortChildDrawingOrderMethod = BaseActivity.class.getDeclaredMethod("sortChildDrawingOrder");
//                mSortChildDrawingOrderMethod.setAccessible(true);
//            }
//            mSortChildDrawingOrderMethod.invoke(this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void test(int a, int b) {


    }
//
//    private void test2(int a, int b) {
//
//    }

    public static BaseActivity get() {
        return new BaseActivity();
    }

    @Override
    public void sortChildDrawingOrder() {
        super.sortChildDrawingOrder();
        Log.d("Fuck", "asdasdasd");
    }
}
