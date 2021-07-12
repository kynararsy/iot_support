package com.example.iot_asma_support;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import eu.long1.spacetablayout.SpaceTabLayout;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;

    DatabaseReference myRef;
    DatabaseReference userRef;

    List<History> histories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate:Started");


        FirebaseAuth auth = FirebaseAuth.getInstance();

        //DrawerMenu
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);//Assigning the variable and casting it, then saying where to find it.
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.closed);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nav_view);

        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupDrawerContent(nvDrawer);
        if (auth.getCurrentUser() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }

        ExtendedFloatingActionButton btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                return;
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Account").child(auth.getCurrentUser().getUid());

        List<Fragment> fragmentList = new ArrayList<>();


        myRef.child("Support").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Support support = snapshot.getValue(Support.class);

                fragmentList.add(new HomeFragment(support.getUsername()));

                userRef = database.getReference("Account").child(support.getUid());

                userRef.child("History").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (int i = 0; i < snapshot.getChildrenCount(); i++) {
                            History history = snapshot.child(String.valueOf(i)).getValue(History.class);
                            histories.add(history);
                        }

                        fragmentList.add(new HistoryFragment(getApplicationContext(), histories));
                        fragmentList.add(new HistoryFragment(getApplicationContext(), histories));

                        ViewPager viewPager = findViewById(R.id.viewPager);
                        SpaceTabLayout tabLayout = findViewById(R.id.spaceTabLayout);
                        tabLayout.initialize(viewPager, getSupportFragmentManager(), fragmentList, null);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
}

    