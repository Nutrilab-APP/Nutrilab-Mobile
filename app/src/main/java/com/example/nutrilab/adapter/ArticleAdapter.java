package com.example.nutrilab.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nutrilab.R;
import com.example.nutrilab.data.model.DataNewsItem;
import com.example.nutrilab.ui.WebViewActivity;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private Context context;
    private List<DataNewsItem> newsList;

    public ArticleAdapter(Context context, List<DataNewsItem> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
       ImageView image;
       TextView title, description;

       public ArticleViewHolder(@NonNull View itemView) {
           super(itemView);
           image = itemView.findViewById(R.id.newsImage);
           title = itemView.findViewById(R.id.newsTitle);
           description = itemView.findViewById(R.id.newsDescription);
       }
    }

    @NonNull
    @Override
    public ArticleAdapter.ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
       return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ArticleViewHolder holder, int position) {
        DataNewsItem newsItem = newsList.get(position);
        holder.title.setText(newsItem.getTitle());
        holder.description.setText(newsItem.getDescription());

        Glide.with(context)
                .load(newsItem.getImageUrl())
                .into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra("url", newsItem.getWebUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
