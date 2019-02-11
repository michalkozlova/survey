package michal.edu.first.Store;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import michal.edu.first.R;
import michal.edu.first.Store.Java.Branch;
import michal.edu.first.Store.Java.BranchListener;
import michal.edu.first.Store.Java.Store;
import michal.edu.first.Store.Java.StoreListener;
import michal.edu.first.Store.Java.StoreRepo;
import michal.edu.first.Store.RecyclerBranchItem.BranchFragment;

public class StoreActivity extends AppCompatActivity {

    TextView storeName;
    Store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        storeName = findViewById(R.id.storeName);


        new StoreRepo().getStoreFromFirebase(new StoreListener() {
            @Override
            public void onStoreCallBack(Store store) {
                storeName.setText(store.getStoreNameEng());

                //getSupportFragmentManager().beginTransaction().replace(R.id.branchContainer, BranchFragment.newInstance(store)).commit();
                new StoreRepo().getBranchesFromFireBase(new BranchListener() {
                    @Override
                    public void onBranchCallback(ArrayList<Branch> branches) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.branchContainer, BranchFragment.newInstance(branches)).commit();
                    }
                });
            }
        });






        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StoreActivity.this, NewBranchActivity.class));
            }
        });
    }

}
