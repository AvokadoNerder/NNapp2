package ru.worldskills.nnapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText logReg, pass1, pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Регстрация");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(Main2Activity.this, ProfileActivity.class);
            startActivity(intent);
        }

    }

    public void onRegClick (View view){
        logReg = (EditText) findViewById(R.id.logReg);
        pass1 = (EditText) findViewById(R.id.pass1);
        pass2 = (EditText) findViewById(R.id.pass2);
        String email = logReg.getText().toString();
        String password = pass1.getText().toString();
        String password2 = pass2.getText().toString();

        if(email == null){
            logReg.setError("Поле пустое!");
        }
        if(password == null){
            pass1.setError("Поле пустое!");
        }
        if(!(email.contains("@"))) {
            logReg.setError("Отсутствует знак @");
        }
        if(password.length() < 6){
            pass1.setError("Длинна пароля должна быть больше, чем 5 символов");
        }
        if(password.equals(password2)) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Main2Activity.this, "Успешная регистрация.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Main2Activity.this, ProfileActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Main2Activity.this, "Ошибка!",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }else {
            pass2.setError("Пароли не совпадают!");
        }

    }


}
