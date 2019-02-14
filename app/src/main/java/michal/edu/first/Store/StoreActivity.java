package michal.edu.first.Store;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import michal.edu.first.MainActivity;
import michal.edu.first.Questionnaire.QuestionnaireActivity;
import michal.edu.first.R;
import michal.edu.first.Store.Java.Branch;
import michal.edu.first.Store.Java.BranchListener;
import michal.edu.first.Store.Java.Store;
import michal.edu.first.Store.Java.StoreListener;
import michal.edu.first.Store.Java.StoreRepo;
import michal.edu.first.Store.RecyclerBranchItem.BranchFragment;
import michal.edu.first.User.User;
import michal.edu.first.User.UserID;

public class StoreActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView storeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        storeName = findViewById(R.id.storeName);
        System.out.println("UserID.thisStore: " + UserID.thisStore);
        System.out.println("thisUser: " + UserID.thisUser);

        if (!UserID.thisUser.getHasStore()) {
            startActivity(new Intent(StoreActivity.this, NewStoreActivity.class));
        } else {
            if (UserID.thisStore == null) {
                new StoreRepo().getStoreFromFirebase(new StoreListener() {
                    @Override
                    public void onStoreCallBack(Store store) {
                        if (store == null) {
                            System.out.println("Store is null return");
                        }
                        UserID.thisStore = store;
                        storeName.setText(store.getStoreNameEng());

                        new StoreRepo().getBranchesFromFireBase(new BranchListener() {
                            @Override
                            public void onBranchCallback(ArrayList<Branch> branches) {
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.branchContainer, BranchFragment.newInstance(branches))
                                        .commit();
                                UserID.thisStore.setBranches(branches);
                                System.out.println("added branches " + UserID.thisStore);
                            }
                        });

                    }
                });
            } else {
                storeName.setText(UserID.thisStore.getStoreNameEng());
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.branchContainer, BranchFragment.newInstance(UserID.thisStore.getBranches()))
                        .commit();
            }
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.branchContainer, new AddBranchFragment()).addToBackStack("").commit();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if (id == R.id.nav_questionnaire) {
            startActivity(new Intent(this, QuestionnaireActivity.class));
        } else if (id == R.id.nav_store) {
            startActivity(new Intent(this, StoreActivity.class));
        } else if (id == R.id.nav_main) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_log_out) {
            UserID.logOut();
            FirebaseAuth.getInstance().signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
