package michal.edu.first.Store;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import michal.edu.first.MainActivity;
import michal.edu.first.Questionnaire.QuestionnaireActivity;
import michal.edu.first.R;
import michal.edu.first.Store.Java.Store;
import michal.edu.first.User.UserID;

public class NewStoreActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final int STORE_RETAIL = 0;
    public static final int STORE_RESTAURANT = 1;

    private int storeType;

    EditText etStoreNameEng, etStoreNameHeb;
    Button btnNext, btnChoseType;
    NumberPicker npBranchNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_store);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        etStoreNameEng = findViewById(R.id.etStoreNameEng);
        etStoreNameHeb = findViewById(R.id.etStoreNameHeb);
        btnNext = findViewById(R.id.btnNext);
        btnChoseType = findViewById(R.id.btnChoseType);
        npBranchNumber = findViewById(R.id.npBranchNumber);

        btnChoseType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(NewStoreActivity.this);
                dialog.setTitle("Choose the type:");
                dialog.setSingleChoiceItems(R.array.types, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        storeType = which;
                    }
                });
                dialog.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (storeType == STORE_RETAIL){
                            btnChoseType.setText("Retail");
                        }else {
                            btnChoseType.setText("Restaurant");
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference newRetail = FirebaseDatabase.getInstance().getReference().child("Stores").child(UserID.userID);
                newRetail.setValue(new Store(storeType, etStoreNameEng.getText().toString(), etStoreNameHeb.getText().toString()));
                UserID.thisUser.setHasStore(true);
                DatabaseReference hasStore = FirebaseDatabase.getInstance().getReference().child("Users").child(UserID.thisUser.getKey()).child("hasStore");
                hasStore.setValue(true);
                startActivity(new Intent(NewStoreActivity.this, StoreActivity.class));
            }
        });


        //TODO: number picker
        npBranchNumber.setMinValue(1);
        npBranchNumber.setMinValue(10);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
            UserID.userID = null;
            UserID.thisUser = null;
            FirebaseAuth.getInstance().signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
