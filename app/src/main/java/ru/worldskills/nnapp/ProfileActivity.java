package ru.worldskills.nnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private TextView myemail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Профиль");
        setContentView(R.layout.activity_profileact);
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        myemail = (TextView) findViewById(R.id.emailAdress);
        myemail.setText(user.getEmail());
    }

    public void profExit(View view){
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        Toast.makeText(ProfileActivity.this, "До свидания, "+user.getEmail() + "!", Toast.LENGTH_SHORT);
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void news(View view){
        Intent intent = new Intent(ProfileActivity.this,Main3Activity.class);
        startActivity(intent);
    }

    public void chat(View view){
        Intent intent = new Intent(ProfileActivity.this,ChatActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            Toast.makeText(ProfileActivity.this,"ДАБ ДАБ ДАБ!!!!", Toast.LENGTH_SHORT);
            return true;
        }
        Toast.makeText(ProfileActivity.this,"ДАБ ДАБ ДАБ!!!!", Toast.LENGTH_SHORT);
        return super.onKeyDown(keyCode, event);
    }

}
