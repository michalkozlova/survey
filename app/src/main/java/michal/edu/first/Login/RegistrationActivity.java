package michal.edu.first.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import michal.edu.first.MainActivity;
import michal.edu.first.R;
import michal.edu.first.UserID;

public class RegistrationActivity extends AppCompatActivity implements OnFailureListener {

    EditText etFirstName, etLastName, etEmailAddress, etPassword, etConfirmPassword;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmailAddress = findViewById(R.id.etEmailAddress);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmailValid() | !isPasswordValid() | !isFirstNameValid() | !isLastNameValid() | !isPasswordConfirmed()){
                    return;
                }

                showProgress(true);

                Task<AuthResult> task = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email(), password());
                task.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        DatabaseReference newUser = FirebaseDatabase.getInstance().getReference().child("Users").push();
                        newUser.setValue(new User(newUser.getKey(), FirebaseAuth.getInstance().getCurrentUser().getUid(), email(), firstName(), lastName()));

                        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
                task.addOnFailureListener(RegistrationActivity.this);
            }
        });
    }

    String email(){return etEmailAddress.getText().toString();}
    String password(){return etPassword.getText().toString();}
    String confirmedPassword(){return etConfirmPassword.getText().toString();}
    String firstName(){return etFirstName.getText().toString();}
    String lastName(){return etLastName.getText().toString();}
    ProgressDialog dialog;

    private void showProgress(boolean show){
        if (dialog == null) {
            dialog = new ProgressDialog(this);

            dialog.setCancelable(true);
            dialog.setTitle("Please wait");
            dialog.setMessage("You will be registered soon!");
        }
        if (show){
            dialog.show();
        }else {
            dialog.dismiss();
        }
    }

    private boolean isFirstNameValid(){
        if (firstName().isEmpty()){
            etFirstName.setError("Please put first name");
            return false;
        } else {
            return true;
        }
    }
    private boolean isLastNameValid(){
        if (firstName().isEmpty()){
            etLastName.setError("Please put last name");
            return false;
        } else {
            return true;
        }
    }
    private boolean isEmailValid(){
        if (email().isEmpty()){
            etEmailAddress.setError(getText(R.string.empty_email));
            return false;
        }
        boolean isEmailValid = email().matches(Patterns.EMAIL_ADDRESS.pattern());
        if(!isEmailValid){
            etEmailAddress.setError(getText(R.string.invalid_email));
        }
        return isEmailValid;
    }
    private boolean isPasswordValid(){
        if (password().isEmpty()){
            etPassword.setError(getText(R.string.empty_password));
            return false;
        }
        boolean isPasswordValid = password().length() >= 6;
        if (!isPasswordValid){
            etPassword.setError(getText(R.string.invalid_password));
        }
        return isPasswordValid;
    }
    private boolean isPasswordConfirmed(){
        if (confirmedPassword().isEmpty()){
            etConfirmPassword.setError("Please confirm password");
            return false;
        } else if (!confirmedPassword().equals(password())){
            etConfirmPassword.setError("Password is not the same");
            return false;
        } else {
            return true;
        }
    }


    //TODO: put this into task.addOnFailureListener
    @Override
    public void onFailure(@NonNull Exception e) {
        showProgress(false);
        Snackbar.make(btnNext, e.getLocalizedMessage(), Snackbar.LENGTH_INDEFINITE).setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }
}
