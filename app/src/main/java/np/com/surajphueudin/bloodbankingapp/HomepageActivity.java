package np.com.surajphueudin.bloodbankingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import np.com.surajphueudin.bloodbankingapp.fragments.FindDonorsFragment;
import np.com.surajphueudin.bloodbankingapp.fragments.HistoryFragment;
import np.com.surajphueudin.bloodbankingapp.fragments.ProfileFragment;
import np.com.surajphueudin.bloodbankingapp.fragments.RequestsFragment;

public class HomepageActivity extends AppCompatActivity {


//    @SuppressLint("NonConstantResourceId")
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//
//        FindDonorsFragment firstFragment = new FindDonorsFragment();
//        RequestsFragment secondFragment = new RequestsFragment();
//        HistoryFragment thirdFragment = new HistoryFragment();
//        ProfileFragment fourthFragment = new ProfileFragment();
//
//        switch (bottomNavigationView.getSelectedItemId()) {
//            case R.id.nav_menu_find_donors:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, firstFragment).commit();
//                break;
//
//            case R.id.nav_menu_requests:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, secondFragment).commit();
//                break;
//
//            case R.id.nav_menu_history:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, thirdFragment).commit();
//                break;
//
//            case R.id.nav_menu_profile:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fourthFragment).commit();
//                break;
//
//            default:
//                Toast.makeText(getApplicationContext(), "Wrong Item", Toast.LENGTH_SHORT).show();
//        }
//    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        FloatingActionButton addBtn = findViewById(R.id.floating_add_btn);

        FindDonorsFragment firstFragment = new FindDonorsFragment();
        RequestsFragment secondFragment = new RequestsFragment();
        HistoryFragment thirdFragment = new HistoryFragment();
        ProfileFragment fourthFragment = new ProfileFragment();

        bottomNavigationView.setSelectedItemId(R.id.nav_menu_find_donors);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, firstFragment).commit();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomepageActivity.this, AddBloodRequestActivity.class);
                startActivity(intent);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_menu_find_donors:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, firstFragment).commit();
                        break;
 
                    case R.id.nav_menu_requests:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, secondFragment).commit();
                        break;

                    case R.id.nav_menu_history:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, thirdFragment).commit();
                        break;

                    case R.id.nav_menu_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fourthFragment).commit();
                        break;

                    default:
                        Toast.makeText(getApplicationContext(), "Wrong Item", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }
}