package michal.edu.first.Store.RecyclerBranchItem;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import michal.edu.first.R;
import michal.edu.first.Store.AddBranchFragment;
import michal.edu.first.Store.Java.Branch;

/**
 * A simple {@link Fragment} subclass.
 */
public class BranchFragment extends Fragment {

    private BranchAdapter adapter;
    private RecyclerView rvBranches;
    FloatingActionButton fab;

    public static BranchFragment newInstance(ArrayList<Branch> branches) {

        Bundle args = new Bundle();
        args.putSerializable("branches", branches);
        BranchFragment fragment = new BranchFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_branch, container, false);
        rvBranches = v.findViewById(R.id.rvBranches);

        ArrayList<Branch> branches = (ArrayList<Branch>) getArguments().getSerializable("branches");
        adapter = new BranchAdapter(branches, getActivity());
        rvBranches.setLayoutManager(new LinearLayoutManager(getContext()));
        rvBranches.setAdapter(adapter);


        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                        .replace(R.id.branchContainer, new AddBranchFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;
    }

}
