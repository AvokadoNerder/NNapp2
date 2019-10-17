package ru.worldskills.nnapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ndetails extends AppCompatActivity {

    TextView nheader, ntext, ndate;
    ImageView nimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndetails);

        ActionBar actBar = getSupportActionBar();

        setTitle("Подробно");
        actBar.setDisplayHomeAsUpEnabled(true);
        actBar.setDisplayShowHomeEnabled(true);

        nheader = findViewById(R.id.nheader);
        ntext = findViewById(R.id.ntext);
        ndate = findViewById(R.id.ndate);
        nimage = findViewById(R.id.nimage);

        String header = getIntent().getStringExtra("header");
        nheader.setText(header);
        String text = getIntent().getStringExtra("text");
        ntext.setText(text);
        String date = getIntent().getStringExtra("date");
        ndate.setText(date);
        String image = getIntent().getStringExtra("image");
        Picasso.get().load(image).into(nimage);
    }

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
