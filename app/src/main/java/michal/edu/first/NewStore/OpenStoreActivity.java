package michal.edu.first.NewStore;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import michal.edu.first.MainActivity;
import michal.edu.first.R;
import michal.edu.first.UserID;

public class OpenStoreActivity extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Stores").child(UserID.userID);
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_store);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (ref == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new NewStoreFragment()).commit();
        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new NewAddressFragment()).commit();
        }
    }

}
