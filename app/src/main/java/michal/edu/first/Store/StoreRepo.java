package michal.edu.first.Store;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import michal.edu.first.UserID;

public class StoreRepo{

    public void getStoreFromFirebase(final StoreListener callback){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Stores").child(UserID.userID);
        final ArrayList<Store> mStores = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String storeNameEng = snapshot.child("storeNameEng").getValue(String.class);
                    String storeNameHeb = snapshot.child("storeNameHeb").getValue(String.class);
                    Integer storeType = snapshot.child("storeType").getValue(Integer.class);

                    Store store = new Store(storeType, storeNameEng, storeNameHeb);
                    mStores.add(store);
                }

                callback.onStoreCallBack(mStores.get(0));
                System.out.println(mStores.get(0));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError);
            }
        });
    }
}
