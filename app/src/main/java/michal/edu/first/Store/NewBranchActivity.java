package michal.edu.first.Store;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import michal.edu.first.R;
import michal.edu.first.UserID;

public class NewBranchActivity extends AppCompatActivity {

    Button btnDone;
    EditText etBranchNameEng, etBranchNameHeb, etCity, etStreet, etNum, etPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_branch);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnDone = findViewById(R.id.btnDone);
        etBranchNameEng = findViewById(R.id.etBranchNameEng);
        etBranchNameHeb = findViewById(R.id.etBranchNameHeb);
        etCity = findViewById(R.id.etCity);
        etStreet = findViewById(R.id.etStreet);
        etNum = findViewById(R.id.etNum);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress(true);

                int num = Integer.valueOf(etNum.getText().toString());
                String street = etStreet.getText().toString();
                String city = etCity.getText().toString();
                Address address = new Address(city, street, num);

                String branchNameEng = etBranchNameEng.getText().toString();
                String branchNameHeb = etBranchNameHeb.getText().toString();
                String branchPhone = etPhoneNumber.getText().toString();
                Branch branch = new Branch(branchNameEng, branchNameHeb, branchPhone, address);

//                DatabaseReference newRetail = FirebaseDatabase.getInstance().getReference().child("Stores").child(UserID.userID);
//                newRetail.setValue(new Store(storeType, etStoreNameEng.getText().toString(), etStoreNameHeb.getText().toString()));

                DatabaseReference newBranch = FirebaseDatabase.getInstance().getReference().child("Stores").child(UserID.userID).child("branches").push();
                newBranch.setValue(branch);


                startActivity(new Intent(NewBranchActivity.this, StoreActivity.class));
            }
        });

    }

    ProgressDialog dialog;

    private void showProgress(boolean show){
        if (dialog == null) {
            dialog = new ProgressDialog(this);

            dialog.setCancelable(true);
            dialog.setTitle("Please wait");
            dialog.setMessage("Loading...");
        }
        if (show){
            dialog.show();
        }else {
            dialog.dismiss();
        }
    }

}