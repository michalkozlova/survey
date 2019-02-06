package michal.edu.first.Questionnaire.Java;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

import michal.edu.first.MainActivity;
import michal.edu.first.R;
import michal.edu.first.UserID;

public class QuestionnaireRepo {

    public void fireOrJson(final Context context, final SectionListener callback) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference questionnaires = reference.child("Questionnaires").child(UserID.userID).child("sections");
        final ArrayList<Section> mSections = new ArrayList<>();
        questionnaires.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Section value = snapshot.getValue(Section.class);
                    mSections.add(value);
                }


                if (mSections.isEmpty()) {
                    //get from Json AND tell the listener that we got the data:
                    callback.onSectionCallBack(importFromJSON(context));
                } else {
                    //sections from firebase AND tell the listener that we got the data:
                    callback.onSectionCallBack(new FullQuiz(mSections));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError);
            }
        });
    }

    public void getFromFirebase(final Context context, final SectionListener callback){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference questionnaires = reference.child("Questionnaires").child(UserID.userID).child("sections");
        final ArrayList<Section> mSections = new ArrayList<>();
        questionnaires.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Section value = snapshot.getValue(Section.class);
                    mSections.add(value);
                }

                    //sections from firebase AND tell the listener that we got the data:
                    callback.onSectionCallBack(new FullQuiz(mSections));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError);
            }
        });
    }

    private FullQuiz importFromJSON(Context context) {
        InputStream resourceReader = context.getResources().openRawResource(R.raw.sample_questionnaire_retail);
        Writer writer = new StringWriter();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonString = writer.toString();

        Gson gson = new Gson();
        return gson.fromJson(jsonString, FullQuiz.class);
    }
}
