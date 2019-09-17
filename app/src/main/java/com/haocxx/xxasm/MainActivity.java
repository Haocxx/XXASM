package com.haocxx.xxasm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public static final String TAG1 = "MainActivity";
    private static final String TAG2 = "MainActivity";
    static final String TAG3 = "MainActivity";
    final String TAG4 = "MainActivity";

    private int a = 1;
    protected int b = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
        test2();
        new A();
        new B();
        b = a;
    }

    private void test() {
        Log.d("Haocxx", new Integer(3).toString());
        Log.d("Haocxx", "test2");
        Log.d("Haocxx", "test3");
        Log.d("Haocxx", "test4");
    }

    void test2() {
        Log.d("Haocxx", new Integer(3).toString());
        Log.d("Haocxx", "test2");
        Log.d("Haocxx", "test3");
        Log.d("Haocxx", "test4");
    }

    private class A {

    }

    private static class B {

    }
}
