package com.haocxx.xxasm;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

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
        findViewById(R.id.button_hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asd++;
            }
        });
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
