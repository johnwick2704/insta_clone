package com.example.insta_clone.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.insta_clone.R;
import com.example.insta_clone.utils.BottomNavigationViewHelper;
import com.example.insta_clone.utils.GridImageAdapter;
import com.example.insta_clone.utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity
{

    private static final String TAG = "ProfileActivity";
    private static final int NUM_GRID_COLUMNS = 3;
    private Context mContext = ProfileActivity.this;
    private static  final int ACTIVITY_NUM = 4;
    private ImageView profilePhoto;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();

//        setupBottomNavigationView();
//        setupToolbar();
//        setupActivityWidgets();
//        tempGridSetup();

    }

    private void init()
    {

        profileFragment fragment = new profileFragment();
        FragmentTransaction transaction = ProfileActivity.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);

        transaction.addToBackStack(getString(R.string.profile_fragment));
        transaction.commit();

    }

//    private void tempGridSetup()
//    {
//        ArrayList<String> imgURLs = new ArrayList<>();
//        imgURLs.add("https://pyxis.nymag.com/v1/imgs/55b/438/d732205198d1fc4b0aafc8bb302e4e68c2-john-wick.2x.rsocial.w600.jpg");
//        imgURLs.add("https://i.ytimg.com/vi/0ESDnRQIX7A/maxresdefault.jpg");
//        imgURLs.add("https://img.cinemablend.com/filter:scale/quill/9/5/8/7/f/6/9587f6c778d63ba40444c9d133c9a4d8a5ad5b3f.jpg?mw=600");
//        imgURLs.add("data:image/jpeg;base64,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");
//        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRRhj6qpHwIwxq-KC_pDb1bXEGdYtKwWiFXYw&usqp=CAU");
//        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRRhj6qpHwIwxq-KC_pDb1bXEGdYtKwWiFXYw&usqp=CAU");
//        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQyNbEZklVH9R7b6-dPf-G1JVlQr1_zaZ5xnQ&usqp=CAU");
//        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRRcFZqK3F1Az254A5GddkLBkRJyNaJ04nh6w&usqp=CAU");
//        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR9r1lJhsy2EFQ0uu8iVeQGe-Ug61X2NBRD_w&usqp=CAU");
//        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR9r1lJhsy2EFQ0uu8iVeQGe-Ug61X2NBRD_w&usqp=CAU");
//        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR9r1lJhsy2EFQ0uu8iVeQGe-Ug61X2NBRD_w&usqp=CAU");
//        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQkClTP_daXM-wS2fb9_dIjPHuI98iw79n5Pg&usqp=CAU");
//        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ1-I17drK45C3qwYFMDnfb6t6-2vzK-rRjZQ&usqp=CAU");
//        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQehag11gq-322hNgvpxXNOI_9pgvcz8_jl5A&usqp=CAU");
//        imgURLs.add("https://pmcdeadline2.files.wordpress.com/2019/05/john-wick-3-jw3_d30_10548_cr_rgb.jpg?crop=0px%2C0px%2C5836px%2C3270px&resize=681%2C383");
//
//        setupImageGrid(imgURLs);
//    }
//
//    private void setupImageGrid(ArrayList<String> imgURLs)
//    {
//        GridView gridView = findViewById(R.id.gridView);
//
//        int gridWidth  = getResources().getDisplayMetrics().widthPixels;
//        int imageWidth = gridWidth/NUM_GRID_COLUMNS;
//        gridView.setColumnWidth(imageWidth);
//
//        GridImageAdapter adapter = new GridImageAdapter(mContext, R.layout.layout_grid_imageview, "", imgURLs);
//        gridView.setAdapter(adapter);
//    }
//
//    private void setProfileImage()
//    {
//        String img = "https://cdn.mos.cms.futurecdn.net/5NyzBxijspGUiFyCiz9F4-1200-80.jpg";
//        UniversalImageLoader.setImage(img, profilePhoto, mProgressBar, " ");
//    }
//
//    private void setupActivityWidgets()
//    {
//        mProgressBar = findViewById(R.id.profileProgressBar);
//        mProgressBar.setVisibility(View.GONE);
//
//        profilePhoto = findViewById(R.id.profile_photo);
//    }
//
//    private void setupToolbar()
//    {
//        Toolbar toolbar = findViewById(R.id.profileToolBar);
//        setSupportActionBar(toolbar);
//
//        ImageView profileMenu = findViewById(R.id.profileMenu);
//        profileMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, AccountSettingsActivity.class);
//                startActivity(intent);
//            }
//        });
//
//    }
//
//    private void setupBottomNavigationView(){
//        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
//        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
//        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
//        BottomNavigationViewHelper.enableNavigation(mContext, this,bottomNavigationViewEx);
//        Menu menu = bottomNavigationViewEx.getMenu();
//        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
//        menuItem.setChecked(true);
//    }
}


