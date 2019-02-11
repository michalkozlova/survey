package michal.edu.first.Store;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import michal.edu.first.R;
import michal.edu.first.Store.Java.Branch;
import michal.edu.first.UserID;

/**
 * A simple {@link Fragment} subclass.
 */
public class FullBranchDetailsFragment extends Fragment {

    TextView tvBranchName, tvAddress, tvPhone;
    Button btnDelete;

    public static FullBranchDetailsFragment newInstance(Branch branch) {

        Bundle args = new Bundle();
        args.putSerializable("branch", branch);
        FullBranchDetailsFragment fragment = new FullBranchDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_full_branch_details, container, false);

        tvBranchName = v.findViewById(R.id.tvBranchName);
        tvAddress = v.findViewById(R.id.tvAddress);
        tvPhone = v.findViewById(R.id.tvPhone);
        btnDelete = v.findViewById(R.id.btnDelete);

        final Branch branch = (Branch) getArguments().getSerializable("branch");
        tvBranchName.setText(branch.getBranchNameEng());
        tvAddress.setText(branch.getBranchAddress().getCity());
        tvPhone.setText(branch.getBranchPhone());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Stores").child(UserID.userID).child("branches").child(branch.getBranchID()).removeValue();
            }
        });

        return v;
    }

}
