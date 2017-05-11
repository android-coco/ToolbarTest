package org.yh.toolbartest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class NormalToolbarActivity extends AppCompatActivity
{

    public Toolbar mNormalToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_toolbar);
        StatusBarCompat.compat(this, 0xFF303F9F);
        initToolbar();
    }

    private void initToolbar()
    {
        mNormalToolbar= (Toolbar) findViewById(R.id.toolbar_normal);
        //设置menu
        mNormalToolbar.inflateMenu(R.menu.menu);
        //设置menu的点击事件
        mNormalToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {

                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_search)
                {
                    Toast.makeText(NormalToolbarActivity.this, R.string.menu_search, Toast
                            .LENGTH_SHORT).show();

                }
                else if (menuItemId == R.id.action_notification)
                {
                    Toast.makeText(NormalToolbarActivity.this, R.string.menu_notification, Toast
                            .LENGTH_SHORT).show();

                }
                else if (menuItemId == R.id.action_item_one)
                {
                    Toast.makeText(NormalToolbarActivity.this, R.string.item_one, Toast
                            .LENGTH_SHORT).show();

                }
                else if (menuItemId == R.id.action_item_two)
                {
                    Toast.makeText(NormalToolbarActivity.this, R.string.item_two, Toast
                            .LENGTH_SHORT).show();

                }
                return true;
            }
        });
        //设置左侧NavigationIcon点击事件
        mNormalToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(NormalToolbarActivity.this, "点击了左侧按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
