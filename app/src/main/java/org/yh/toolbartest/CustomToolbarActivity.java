package org.yh.toolbartest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CustomToolbarActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_toolbar);
        StatusBarCompat.compat(this, 0xFFFF4081);
    }
}
