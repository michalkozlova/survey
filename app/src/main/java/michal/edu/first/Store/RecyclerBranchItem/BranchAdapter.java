package michal.edu.first.Store.RecyclerBranchItem;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import michal.edu.first.R;
import michal.edu.first.Store.FullBranchDetailsFragment;
import michal.edu.first.Store.Java.Branch;
import michal.edu.first.Store.StoreActivity;

public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.BranchViewHolder>{

    private List<Branch> branches;
    private FragmentActivity activity;

    public BranchAdapter(List<Branch> branches, FragmentActivity activity) {
        this.branches = branches;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BranchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item_branch, viewGroup, false);
        return new BranchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BranchViewHolder branchViewHolder, final int i) {
        final Branch branch = branches.get(i);

        branchViewHolder.tvBranchName.setText(branch.getBranchNameEng());
        branchViewHolder.tvBranchAddress.setText(branch.getBranchAddress().getCity() + ", " + branch.getBranchAddress().getStreet());

        branchViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.branchContainer, FullBranchDetailsFragment.newInstance(branches.get(i)))
                        .addToBackStack("")
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return branches.size();
    }

    public class BranchViewHolder extends RecyclerView.ViewHolder {

        TextView tvBranchName, tvBranchAddress;

        public BranchViewHolder(@NonNull View itemView) {
            super(itemView);

            tvBranchName = itemView.findViewById(R.id.tvBranchName);
            tvBranchAddress = itemView.findViewById(R.id.tvBranchAddress);
        }
    }

}
