package com.ortho.medicare.medicareortho.activities;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.ortho.medicare.medicareortho.R;
import com.ortho.medicare.medicareortho.customviews.CustomTextView;
import com.ortho.medicare.medicareortho.fragments.AboutUsFragment;
import com.ortho.medicare.medicareortho.fragments.ContactFragment;
import com.ortho.medicare.medicareortho.fragments.InquiryFragment;
import com.ortho.medicare.medicareortho.fragments.ProductListFragment;
import com.ortho.medicare.medicareortho.fragments.QualityFragment;
import com.ortho.medicare.medicareortho.utils.CommonUtil;

import static android.view.Gravity.START;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private CustomTextView toolbar_title;
    private DrawerLayout mDrawerLayout;
    private CoordinatorLayout mMainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        initializeView();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle(R.string.exit_alert_string);

            dialog.setPositiveButton(getString(R.string.set_yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            dialog.setNegativeButton(getString(R.string.set_no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_about_us) {
            replaceFragment(AboutUsFragment.newInstance());
        } else if (id == R.id.nav_quality) {
            replaceFragment(QualityFragment.newInstance());
        } else if (id == R.id.nav_inquiry) {
            replaceFragment(InquiryFragment.newInstance());
        } else if (id == R.id.nav_contact) {
            replaceFragment(ContactFragment.newInstance());
        } else if (id == R.id.nav_products) {
            replaceFragment(ProductListFragment.newInstance());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * This method is used to initialize views.
     *
     * @Date : 01/06/2017
     * @author : Harsh Patel
     */
    private void initializeView() {

        CommonUtil.hideSoftKeyboard(MainActivity.this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (CustomTextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mMainView = (CoordinatorLayout) findViewById(R.id.main_view);

        setDrawerLayout();
        replaceFragment(ProductListFragment.newInstance());
    }

    /**
     * This method is used to initialize drawer Layout.
     *
     * @Date : 09/06/2017
     * @author : Harsh Patel
     */
    private void setDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 0);
                mMainView.setTranslationX(slideOffset * drawerView.getWidth());

                // Apply animation to image view
                mDrawerLayout.bringChildToFront(drawerView);
                mDrawerLayout.requestLayout();
            }
        };
//        toggle.setDrawerIndicatorEnabled(false);
//        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_menu, this.getTheme());
//        toggle.setHomeAsUpIndicator(drawable);

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerVisible(START)) {
                    mDrawerLayout.closeDrawer(START);
                } else {
                    mDrawerLayout.openDrawer(START);
                }
            }
        });

        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
//        mDrawerLayout.setScrimColor(ContextCompat.getColor(MainActivity.this, android.R.color.transparent));

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.add(R.id.act_main_container, fragment);

        fragmentTransaction.commit();
        mDrawerLayout.closeDrawer(START);
    }

}
