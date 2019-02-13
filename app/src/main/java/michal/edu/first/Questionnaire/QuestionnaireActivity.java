package michal.edu.first.Questionnaire;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import michal.edu.first.MainActivity;
import michal.edu.first.Questionnaire.Java.FullQuiz;
import michal.edu.first.Questionnaire.Java.Question;
import michal.edu.first.Questionnaire.Java.QuestionnaireRepo;
import michal.edu.first.Questionnaire.Java.SectionListener;
import michal.edu.first.Questionnaire.RecyclerQuestionItem.QuestionFragment;
import michal.edu.first.R;
import michal.edu.first.Store.StoreActivity;
import michal.edu.first.User.UserID;

public class QuestionnaireActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tvFirstSection = findViewById(R.id.tvFirstSection);
        tvSecondSection = findViewById(R.id.tvSecondSection);
        tvThirdSection = findViewById(R.id.tvThirdSection);

        showProgress(true);

        //TODO: why he doesn't do it from the first time?????

        if (currentFullQuiz == null) {
            new QuestionnaireRepo().fireOrJson(this, new SectionListener() {
                @Override
                public void onSectionCallBack(FullQuiz fullQuiz) {

                    currentFullQuiz = fullQuiz;

                    section1 = QuestionFragment.newInstance(fullQuiz.getSections().get(0));
                    section2 = QuestionFragment.newInstance(fullQuiz.getSections().get(1));
                    section3 = QuestionFragment.newInstance(fullQuiz.getSections().get(2));

                    tvFirstSection.setText(fullQuiz.getSections().get(0).getSectionName());
                    tvSecondSection.setText(fullQuiz.getSections().get(1).getSectionName());
                    tvThirdSection.setText(fullQuiz.getSections().get(2).getSectionName());

                    getSupportFragmentManager().beginTransaction().replace(R.id.firstSection, section1).commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.secondSection, section2).commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.thirdSection, section3).commit();

                    showProgress(false);
                    System.out.println(currentFullQuiz.getSections().size());
                }
            });
        }

        //TODO: ask to save changes before leaving the activity
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
            case R.id.action_save:
                saveChanges();
                break;
        }

        return true;
    }

    private void saveChanges() {
        DatabaseReference newQuestionnaire = FirebaseDatabase.getInstance().getReference().child("Questionnaires").child(UserID.userID);
        newQuestionnaire.setValue(currentFullQuiz);
        System.out.println(currentFullQuiz.getSections().size());
        //TODO: fix it
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
                System.out.println(sectionID);
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
                addQuestionType();
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

    private void addQuestionType() {
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
        dialog.show();
    }

    ProgressDialog dialog;

    private void showProgress(boolean show){
        if (dialog == null) {
            dialog = new ProgressDialog(this);

            dialog.setCancelable(true);
            dialog.setTitle("Please wait");
            dialog.setMessage("Loading...");
        }
        if (show){
            dialog.show();
        }else {
            dialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.nav_questionnaire) {
            startActivity(new Intent(this, QuestionnaireActivity.class));
        } else if (id == R.id.nav_store) {
            startActivity(new Intent(this, StoreActivity.class));
        } else if (id == R.id.nav_main) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_log_out) {
            UserID.userID = null;
            UserID.thisUser = null;
            FirebaseAuth.getInstance().signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}