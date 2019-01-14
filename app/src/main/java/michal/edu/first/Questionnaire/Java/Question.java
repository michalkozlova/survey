package michal.edu.first.Questionnaire.Java;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Question implements Serializable {

    @SerializedName("question")
    private String questionText;
    @SerializedName("type")
    private int questionType;

    public static final int YES_NO = 0;
    public static final int ONE_FIVE = 1;

    public Question(String questionText, int questionType) {
        this.questionText = questionText;
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                ", questionType=" + questionType +
                '}';
    }
}
