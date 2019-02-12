package michal.edu.first.User;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserRepo {

    public void getUserFromFirebase(final UserListener callback){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        final ArrayList<User> users = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User value = snapshot.getValue(User.class);
                    users.add(value);
                }

                callback.onUserCallback(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError);
            }
        });
    }

}
