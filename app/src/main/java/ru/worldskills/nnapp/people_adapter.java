package ru.worldskills.nnapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class people_adapter extends RecyclerView.Adapter<people_adapter.PeopleHolderView> {

ArrayList<people_getset> people;

public people_adapter(ArrayList<people_getset> p){
    people = p;
}


    @NonNull
    @Override
    public people_adapter.PeopleHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_shablon,parent,false);
        return new people_adapter.PeopleHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull people_adapter.PeopleHolderView holder, int position) {
        holder.name.setText(people.get(position).getName());
        holder.surname.setText(people.get(position).getSurName());
        Picasso.get().load(people.get(position).getImage()).transform(new Circular()).into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    class PeopleHolderView extends RecyclerView.ViewHolder{
        TextView name, surname;
        ImageView pic;
        public PeopleHolderView(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.card_name);
            surname = (TextView) itemView.findViewById(R.id.card_surname);
            pic = (ImageView) itemView.findViewById(R.id.card_image);
    }
}}