package com.haocxx.xxasm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private int a = 1;
    protected int b = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
        new A();
        new B();
        b = a;
    }

    private void test() {

    }

    private class A {

    }

    private static class B {

    }
}
