package michal.edu.first.Questionnaire;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import michal.edu.first.MainActivity;
import michal.edu.first.Questionnaire.Java.FullQuiz;
import michal.edu.first.Questionnaire.Java.Question;
import michal.edu.first.Questionnaire.RecyclerQuestionItem.QuestionAdapter;
import michal.edu.first.Questionnaire.RecyclerQuestionItem.QuestionFragment;
import michal.edu.first.R;

public class QuestionnaireActivity extends AppCompatActivity {

    public static FullQuiz currentFullQuiz;
    TextView tvFirstSection, tvSecondSection, tvThirdSection;
    private QuestionFragment section1;
    private QuestionFragment section2;
    private QuestionFragment section3;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tvFirstSection = findViewById(R.id.tvFirstSection);
        tvSecondSection = findViewById(R.id.tvSecondSection);
        tvThirdSection = findViewById(R.id.tvThirdSection);

        if (currentFullQuiz == null) {
            currentFullQuiz = FullQuiz.importFromJSON(this);
        }

        section1 = QuestionFragment.newInstance(currentFullQuiz.getSections().get(0));
        section2 = QuestionFragment.newInstance(currentFullQuiz.getSections().get(1));
        section3 = QuestionFragment.newInstance(currentFullQuiz.getSections().get(2));

        tvFirstSection.setText(currentFullQuiz.getSections().get(0).getSectionName());
        tvSecondSection.setText(currentFullQuiz.getSections().get(1).getSectionName());
        tvThirdSection.setText(currentFullQuiz.getSections().get(2).getSectionName());

        getSupportFragmentManager().beginTransaction().replace(R.id.firstSection, section1).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.secondSection, section2).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.thirdSection, section3).commit();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_questionnaire, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_add:
                addQuestion();
                break;
        }

        return true;
    }

    private int sectionID;
    private int questionType;
    private String newQuestion;

    private void addQuestion() {
        String[] sections = new String[currentFullQuiz.getSections().size()];
        for (int i = 0; i < currentFullQuiz.getSections().size(); i++) {
            sections[i] = currentFullQuiz.getSections().get(i).getSectionName();
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Add question to the section:");
        dialog.setSingleChoiceItems(sections, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sectionID = which;
            }
        });

        dialog.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addQuestionText();
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private void addQuestionText() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Write a question:");
        dialog.setView(R.layout.edit_question_text);
        dialog.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (alertDialog != null){
                    EditText editText = alertDialog.findViewById(R.id.etNewQuestionText);
                    newQuestion = editText.getText().toString();
                    alertDialog = null;
                }
                addQuestionType().show();
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog = dialog.show();
    }

    private AlertDialog.Builder addQuestionType() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Set question's type:");
        dialog.setSingleChoiceItems(R.array.questionTypes, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                questionType = which;
            }
        });
        dialog.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Question q = new Question(newQuestion, questionType);
                QuestionFragment[] arr = {section1, section2, section3};
                arr[sectionID].addQuestion(q);
                dialog.dismiss();
            }
        });
        return dialog;
    }
}
