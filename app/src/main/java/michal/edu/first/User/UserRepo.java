package michal.edu.first.User;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class UserRepo {

    public void getUserFromFirebase(final UserListener callback){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        final ArrayList<User> users = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, Object> map = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, Object>>() {
                });

                String email = (String) map.get("email");
                String firstName = (String) map.get("firstName");
                String lastName = (String) map.get("lastName");
                Boolean hasStore = (Boolean) map.get("hasStore");

                UserID.thisUser = new User(email, firstName, lastName, hasStore);
                users.add(UserID.thisUser);

                callback.onUserCallback(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError);
            }
        });
    }

}