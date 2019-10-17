package ru.worldskills.nnapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class news_adapter extends RecyclerView.Adapter<news_adapter.ViewHolder> {

    ArrayList<newsgetset> News;

    public news_adapter(ArrayList<newsgetset> news) {
        News = news;
    }


    @NonNull
    @Override
    public news_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_shablon,parent,false);
        return new news_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.news_head.setText(News.get(position).getHeader());
        holder.news_text.setText(News.get(position).getText());
        Picasso.get().load(News.get(position).getImage()).into(holder.news_image);
        holder.news_date.setText(News.get(position).getDate());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ndetails.class);
                intent.putExtra("header", News.get(position).getHeader());
                intent.putExtra("text", News.get(position).getText());
                intent.putExtra("date", News.get(position).getDate());
                intent.putExtra("image", News.get(position).getImage());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return News.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView news_head, news_text, news_date;
        ImageView news_image;
        CardView cardView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            news_head = itemView.findViewById(R.id.news_header);
            news_text = itemView.findViewById(R.id.news_text);
            news_date = itemView.findViewById(R.id.news_date);
            news_image = itemView.findViewById(R.id.news_image);
            cardView = itemView.findViewById(R.id.card_news);
        }
    }
}
