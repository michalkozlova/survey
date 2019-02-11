package michal.edu.first.Store;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import michal.edu.first.R;
import michal.edu.first.Store.Java.Store;
import michal.edu.first.UserID;

public class NewStoreActivity extends AppCompatActivity {

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
                startActivity(new Intent(NewStoreActivity.this, StoreActivity.class));
            }
        });


        //TODO: number picker
        npBranchNumber.setMinValue(1);
        npBranchNumber.setMinValue(10);
    }

}
