package com.developer.chithlal.mjc.app.Base;

import static com.developer.chithlal.mjc.app.util.Constants.PERMISSION_CAMERA;
import static com.developer.chithlal.mjc.app.util.Constants.PERMISSION_COARSE_LOCATION;
import static com.developer.chithlal.mjc.app.util.Constants.PERMISSION_REQ_CODE;
import static com.developer.chithlal.mjc.app.util.Constants.PERMISSION_STORAGE_WRITE;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;


import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.app.util.PermissionManager;
import com.developer.chithlal.mjc.databinding.ActivityBaseBinding;
import com.developer.chithlal.mjc.root.App;
import com.developer.chithlal.mjc.root.account_manager.AccountManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class BaseActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private User mUser;
    ActivityBaseBinding mBinding;
    AccountManager mAccountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityBaseBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mToolbar = mBinding.baseToolbar;
        mAccountManager = new AccountManager(this);

        mUser = mAccountManager.getUser();

        //Toolbar setup
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setTitle("");
        mToolbar.setEnabled(true);
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar!=null)
            mActionBar.setDisplayHomeAsUpEnabled(true);
        BottomNavigationView navView = mBinding.navView;

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);  // Hostfragment
        NavInflater inflater = navHostFragment.getNavController().getNavInflater();
        NavGraph graph = inflater.inflate(R.navigation.mobile_navigation);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.nav_profile, R.id.navigation_orders)
                .build();

        //set nav controller from fragment


            graph.setStartDestination(R.id.navigation_home);

        //navHostFragment.getNavController().setGraph(graph);
        NavController navController = navHostFragment.getNavController();
        navController.setGraph(graph);

        navController.navigateUp();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        NavigationUI.setupWithNavController(mToolbar, navController);
        getPermissions();
    }
    void getPermissions(){
        PermissionManager permissionManager = new PermissionManager(this);
        permissionManager.checkPermissionIfNotRequest(PERMISSION_COARSE_LOCATION);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_REQ_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Permission granted",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this,"We need this permission to proceed, Please grant it.",Toast.LENGTH_LONG).show();
                }

            }


        }

    }
}
