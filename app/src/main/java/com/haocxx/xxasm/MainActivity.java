package com.haocxx.xxasm;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.lang.reflect.Method;

public class MainActivity extends BaseActivity {

     long a = System.currentTimeMillis();
    protected int b = 1;

//    private static Method mSortChildDrawingOrderMethod = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                a = b;
//                test(a, 1);
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

//    private static void test(int a, int b) {
//
//
//    }
//
//    private void test2(int a, int b) {
//
//    }

}
