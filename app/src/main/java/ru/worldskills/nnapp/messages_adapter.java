package ru.worldskills.nnapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class messages_adapter extends RecyclerView.Adapter<messages_adapter.ViewHolder> {

    ArrayList<messages_get_set> Mess;

    public messages_adapter(ArrayList<messages_get_set> mess){Mess = mess;}

    @NonNull
    @Override
    public messages_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_shablon,parent,false);
        return new messages_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull messages_adapter.ViewHolder holder, int position) {
        holder.message.setText(Mess.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return Mess.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView message;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            message = itemView.findViewById(R.id.message);
        }

    }

}

