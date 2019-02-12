package michal.edu.first.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import michal.edu.first.MainActivity;
import michal.edu.first.R;

public class LoginActivity extends AppCompatActivity implements OnFailureListener {

    EditText etEmail, etPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmailValid() | !isPasswordValid()){
                    return;
                }

                showProgress(true);

                Task<AuthResult> task = FirebaseAuth.getInstance().signInWithEmailAndPassword(email(), password());
                task.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
                task.addOnFailureListener(LoginActivity.this);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
    }

    String email(){return etEmail.getText().toString();}
    String password(){return etPassword.getText().toString();}


    ProgressDialog dialog;

    private void showProgress(boolean show){
        if (dialog == null) {
            dialog = new ProgressDialog(this);

            dialog.setCancelable(true);
            dialog.setTitle("Please wait");
            dialog.setMessage("Logging you in...");
        }
        if (show){
            dialog.show();
        }else {
            dialog.dismiss();
        }
    }

    private boolean isEmailValid(){
        if (email().isEmpty()){
            etEmail.setError(getText(R.string.empty_email));
            return false;
        }
        boolean isEmailValid = email().matches(Patterns.EMAIL_ADDRESS.pattern());
        if(!isEmailValid){
            etEmail.setError(getText(R.string.invalid_email));
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

    //TODO: put this into task.addOnFailureListener
    @Override
    public void onFailure(@NonNull Exception e) {
        showProgress(false);
        Snackbar.make(btnLogin, e.getLocalizedMessage(), Snackbar.LENGTH_INDEFINITE).setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }
}
