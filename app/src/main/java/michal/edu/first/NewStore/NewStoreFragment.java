package michal.edu.first.NewStore;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import michal.edu.first.Questionnaire.Java.Question;
import michal.edu.first.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewStoreFragment extends Fragment {

    public static final int STORE_RETAIL = 0;
    public static final int STORE_RESTAURANT = 1;

    final String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    private int storeType;
    private String StoreNameEng;
    private String StoreNameHeb;

    EditText etStoreNameEng, etStoreNameHeb;
    Button btnNext, btnChoseType;
    NumberPicker npBranchNumber;

    public NewStoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_store, container, false);
        etStoreNameEng = v.findViewById(R.id.etStoreNameEng);
        etStoreNameHeb = v.findViewById(R.id.etStoreNameHeb);
        btnNext = v.findViewById(R.id.btnNext);
        btnChoseType = v.findViewById(R.id.btnChoseType);
        npBranchNumber = v.findViewById(R.id.npBranchNumber);

        btnChoseType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
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
                if (storeType == STORE_RETAIL) {
                    DatabaseReference newRetail = FirebaseDatabase.getInstance().getReference().child("Retail").child(userID);
                    newRetail.setValue(new Store(storeType, etStoreNameEng.getText().toString(), etStoreNameHeb.getText().toString()));
                }else {
                    DatabaseReference newRetail = FirebaseDatabase.getInstance().getReference().child("Restaurant").child(userID);
                    newRetail.setValue(new Store(storeType, etStoreNameEng.getText().toString(), etStoreNameHeb.getText().toString()));
                }
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new NewAddressFragment()).commit();
            }
        });


        //TODO: number picker
        npBranchNumber.setMinValue(1);
        npBranchNumber.setMinValue(10);

        return v;
    }
}
