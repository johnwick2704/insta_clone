package com.example.insta_clone.Share;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.insta_clone.R;
import com.example.insta_clone.utils.BottomNavigationViewHelper;
import com.example.insta_clone.utils.Permissions;
import com.example.insta_clone.utils.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class ShareActivity extends AppCompatActivity
{

    private static  final int VERIFY_PERMISSION_REQUEST = 1;
    private static final String TAG = "ShareActivity";
    private Context context = ShareActivity.this;
    private static  final int ACTIVITY_NUM = 2;
    private ViewPager mViewpager;
    private Context mContext = ShareActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        if (checkPermissionsArray(Permissions.PERMISSIONS))
        {
            setupViewpager();
        }
        else {
            verifyPermissions(Permissions.PERMISSIONS);
        }

        //setupBottomNavigationView();
    }


    //return current tab number

    public int getCurrentTabNumber()
    {
        return  mViewpager.getCurrentItem();
    }


    //setup viewpager for managing the tabs

    private void setupViewpager()
    {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new GalleryFragment());
        adapter.addFragment(new PhotoFragment());

        mViewpager = findViewById(R.id.container);
        mViewpager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabsBottom);
        tabLayout.setupWithViewPager(mViewpager);

        tabLayout.getTabAt(0).setText(getString(R.string.gallery));
        tabLayout.getTabAt(1).setText(getString(R.string.photo));

    }


    public int getTask(){
        Log.d(TAG, "getTask: TASK: " + getIntent().getFlags());
        return getIntent().getFlags();
    }


    public void verifyPermissions(String[] permissions) {
        ActivityCompat.requestPermissions(ShareActivity.this,
                permissions,
                VERIFY_PERMISSION_REQUEST
        );

    }

    public boolean checkPermissionsArray(String[] permissions)
    {
        int i;

        for(i = 0; i < permissions.length; i++)
        {
            String check = permissions[i];
            if (!checkPermissions(check))
            {
                return false;
            }
        }
        return true;
    }

    public boolean checkPermissions(String permission)
    {
        int permissionRequest = ActivityCompat.checkSelfPermission(ShareActivity.this, permission);

        if (permissionRequest != PackageManager.PERMISSION_GRANTED)
        {
            return false;
        }
        else {
            return true;
        }
    }



    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, this,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

}


