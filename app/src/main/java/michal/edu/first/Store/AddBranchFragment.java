package michal.edu.first.Store;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.SQLOutput;

import michal.edu.first.R;
import michal.edu.first.Store.Java.Address;
import michal.edu.first.Store.Java.Branch;
import michal.edu.first.Store.RecyclerBranchItem.BranchFragment;
import michal.edu.first.User.UserID;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddBranchFragment extends Fragment {

    Button btnDone;
    EditText etBranchNameEng, etBranchNameHeb, etCity, etStreet, etNum, etPhoneNumber;


    public AddBranchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add_branch, container, false);

        btnDone = v.findViewById(R.id.btnDone);
        etBranchNameEng = v.findViewById(R.id.etBranchNameEng);
        etBranchNameHeb = v.findViewById(R.id.etBranchNameHeb);
        etCity = v.findViewById(R.id.etCity);
        etStreet = v.findViewById(R.id.etStreet);
        etNum = v.findViewById(R.id.etNum);
        etPhoneNumber = v.findViewById(R.id.etPhoneNumber);

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

                DatabaseReference newBranch = FirebaseDatabase.getInstance().getReference().child("Branches").child(UserID.userID).child(branchNameEng);
                newBranch.setValue(branch);
                UserID.thisBranches.add(branch);

                System.out.println(UserID.thisBranches);

                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.branchContainer, BranchFragment.newInstance(UserID.thisBranches))
                        .commit();
                showProgress(false);
            }
        });


        return v;
    }


    ProgressDialog dialog;

    private void showProgress(boolean show){
        if (dialog == null) {
            dialog = new ProgressDialog(getContext());

            dialog.setCancelable(true);
            dialog.setTitle("Please wait");
            dialog.setMessage("Saving...");
        }
        if (show){
            dialog.show();
        }else {
            dialog.dismiss();
        }
    }

}
