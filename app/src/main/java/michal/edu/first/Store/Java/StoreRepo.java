package michal.edu.first.Store.Java;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import michal.edu.first.User.UserID;

public class StoreRepo{

    public void getStoreFromFirebase(final StoreListener callback){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Stores").child(UserID.userID);
        final ArrayList<Store> mStores = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                HashMap<String, Object> map = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, Object>>() {
                });

                    Number st = (Number) map.get("storeType");
                    String snEng = (String) map.get("storeNameEng");
                    String snHeb = (String) map.get("storeNameHeb");

                    Store store = new Store(st.intValue(), snEng, snHeb);
                    mStores.add(store);

                    callback.onStoreCallBack(mStores.get(0));
                    System.out.println(mStores.get(0));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError);
            }
        });
    }


    public ArrayList<Branch> getBranchesFromFireBase(final BranchListener callback){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Branches").child(UserID.userID);
        final ArrayList<Branch> mBranches = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Branch value = snapshot.getValue(Branch.class);
                    mBranches.add(value);
                }

                if (mBranches.isEmpty()){
                    Branch emptyBranch = new Branch("No branches yet", "-", "00-000-0000", new Address("", "", 0));
                    mBranches.add(emptyBranch);
                    callback.onBranchCallback(mBranches);
                } else {
                    callback.onBranchCallback(mBranches);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError);
            }
        });

        return mBranches;
    }
}
