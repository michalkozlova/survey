package michal.edu.first.Questionnaire.Java;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import michal.edu.first.MainActivity;
import michal.edu.first.Questionnaire.QuestionnaireActivity;
import michal.edu.first.R;

public class FullQuiz implements Serializable{

    @SerializedName("questionnaire")
    private ArrayList<Section> sections;

    public FullQuiz() {
    }

    public FullQuiz(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "FullQuiz{" +
                "sections=" + sections +
                '}';
    }

    public static FullQuiz importFromJSON(Context context) {
        InputStream resourceReader = context.getResources().openRawResource(R.raw.sample_questionnaire_retail);
        Writer writer = new StringWriter();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null){
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
