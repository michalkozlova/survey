
 package michal.edu.first.Questionnaire.Java;


 import com.google.gson.annotations.SerializedName;

 import java.io.Serializable;
 import java.util.ArrayList;

 public class FullQuiz implements Serializable{
//
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


    @Override
    public String toString() {
        return "FullQuiz{" +
                "sections=" + sections +
                '}';
    }


 }

