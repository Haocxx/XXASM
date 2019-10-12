package com.haocxx.xxasm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
//    public static final String TAG1 = "MainActivity";
//    private static final String TAG2 = "MainActivity";
//    static final String TAG3 = "MainActivity";
//    final String TAG4 = "MainActivity";

    private int a = 1;
//    protected int b = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        test(a);
//        test2(a);
//        new A();
//        new B();
//        new Handler().post(() -> {
//            test(a);
//            test2(a);
//        });
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                test(a, 1);
                test2(a, 1);

            }
        };
        new Handler().post(runnable);
//        b = a;
    }

    private static void test(int a, int b) {
//        Log.d("Haocxx", new Integer(3).toString());
//        Log.d("Haocxx", "test2");
//        Log.d("Haocxx", "test3");
//        Log.d("Haocxx", "test4");
//        Log.d("Haocxx", String.valueOf(this.a));

    }

    private void test2(int a, int b) {
//        Log.d("Haocxx", new Integer(3).toString());
//        Log.d("Haocxx", "test2");
//        Log.d("Haocxx", "test3");
//        Log.d("Haocxx", "test4");
    }

//    private class A {
//
//    }
//
//    private static class B {
//
//    }
}
