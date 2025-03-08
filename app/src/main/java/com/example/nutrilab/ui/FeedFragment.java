package com.example.nutrilab.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrilab.R;
import com.example.nutrilab.adapter.ArticleAdapter;
import com.example.nutrilab.data.model.DataNewsItem;
import com.example.nutrilab.data.model.NewsResponse;
import com.example.nutrilab.data.retrofit.ApiService;
import com.example.nutrilab.data.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArticleAdapter adapter;
    private List<DataNewsItem> newsList;
    private TextView txtNewsEmpty;
    
    
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        recyclerView = view.findViewById(R.id.article_rc);
        txtNewsEmpty = view.findViewById(R.id.txt_article_empty);
        
        setupRecyclerView();
        fetchNewsData();
        
        return view;
    }

    private void fetchNewsData() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<NewsResponse> call = apiService.getNews();

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DataNewsItem> fetchedNews = response.body().getData();
                    if (fetchedNews.isEmpty()) {
                        txtNewsEmpty.setVisibility(View.VISIBLE);
                    } else {
                        newsList.clear();
                        newsList.addAll(fetchedNews);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    txtNewsEmpty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Request failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecyclerView() {
        newsList = new ArrayList<>();
        adapter = new ArticleAdapter(getContext(), newsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }
}
