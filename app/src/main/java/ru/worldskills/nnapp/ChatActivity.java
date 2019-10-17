package ru.worldskills.nnapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Messages");

    private static int len = 150;

    ArrayList<messages_get_set> messages = new ArrayList<messages_get_set>();
    LinearLayoutManager linearLayoutManager;

    EditText MessageText;
    Button MessageSendBtn;
    RecyclerView mRecycler;
    messages_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        MessageText = findViewById(R.id.message_input);
        MessageSendBtn = findViewById(R.id.send_message);
        mRecycler = findViewById(R.id.message_recycler);

        linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setReverseLayout(true);
        //linearLayoutManager.setStackFromEnd(true);

        mRecycler.setLayoutManager(linearLayoutManager);
        mRecycler.setHasFixedSize(true);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messages.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {

                    messages_get_set p = dataSnapshot1.getValue(messages_get_set.class);
                    messages.add(p);

                }
                adapter = new messages_adapter(messages);
                //progressBar.setVisibility(View.INVISIBLE);
                mRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //String msg = dataSnapshot.getValue(String.class);
                //messages.add(msg);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void SendClick(View view){
        String msg = MessageText.getText().toString();
        if(msg.equals("")){
            MessageText.setError("Поле пустое");
            return;
        }else if(msg.equals(" ")){MessageText.setError("Поле пустое"); return;}else if (msg.length() > len){MessageText.setError("Длинна сообщения должна быть меньше 150-ти символов"); return;}

        myRef = database.getInstance().getReference("Messages").push().child("text");
        myRef.setValue(msg);
        MessageText.setText("");


    }


}
