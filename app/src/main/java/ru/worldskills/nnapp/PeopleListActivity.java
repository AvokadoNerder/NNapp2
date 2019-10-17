package ru.worldskills.nnapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PeopleListActivity extends AppCompatActivity {

    DatabaseReference myRef;
    RecyclerView recyclerView;
    ArrayList<people_getset> list, list1;
    people_adapter adapter;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);

        setTitle("Список участников");

        recyclerView = (RecyclerView) findViewById(R.id.pep_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myRef = FirebaseDatabase.getInstance().getReference().child("People").child("Expert");

        list = new ArrayList<people_getset>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    people_getset p = dataSnapshot1.getValue(people_getset.class);
                    list.add(p);

                }
                adapter = new people_adapter(list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        bottomNavigationView = findViewById(R.id.tab_pep);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.exp:
                        expert();
                        break;
                    case R.id.ych:
                        ych();
                        break;
                }
                return true;
            }
        });

    }

    private void expert(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<people_getset>();

        myRef = FirebaseDatabase.getInstance().getReference().child("People").child("Expert");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    people_getset p = dataSnapshot1.getValue(people_getset.class);
                    list.add(p);

                }
                adapter = new people_adapter(list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void ych(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<people_getset>();

        myRef = FirebaseDatabase.getInstance().getReference().child("People").child("Ych");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    people_getset p = dataSnapshot1.getValue(people_getset.class);
                    list.add(p);
                }
                adapter = new people_adapter(list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void search(String str){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<people_getset> searchlist = new ArrayList<>();
        for(people_getset object:list1){
            if(object.SurName.toLowerCase().contains(str.toLowerCase()) | object.Name.toLowerCase().contains(str.toLowerCase())){
                searchlist.add(object);
            }
        }
        adapter = new people_adapter(searchlist);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.ych_search, menu);

        final MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView search1 = (SearchView) searchItem.getActionView();

        list1 = new ArrayList<people_getset>();

        full_list("Expert");
        full_list("Ych");
        search1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s != null){
                    search(s);
                    return true;
                }else { return false;}
            }
        });

        return true;
    }

    private void full_list(String str){
        myRef = FirebaseDatabase.getInstance().getReference().child("People").child(str);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    people_getset p = dataSnapshot1.getValue(people_getset.class);
                    list1.add(p);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void  onHomeClick(View view){
        Intent intent = new Intent(PeopleListActivity.this, Main3Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
