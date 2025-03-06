package com.example.nutrilab.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.example.nutrilab.adapter.MyAdapter;
import com.example.nutrilab.R;
import com.example.nutrilab.data.retrofit.ApiService;
import com.example.nutrilab.data.retrofit.RetrofitClient;
import com.example.nutrilab.data.model.HistoryResponse;
import com.example.nutrilab.pref.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<HistoryResponse.HistoryData> historyList;
    private TextView txtHistoryEmpty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.history_recyclervw);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        historyList = new ArrayList<>();
        adapter = new MyAdapter(historyList);
        recyclerView.setAdapter(adapter);

        txtHistoryEmpty = view.findViewById(R.id.txt_history_empty);



        ApiService apiService = RetrofitClient.getApiService();
        String userId = SharedPrefManager.getInstance(getActivity()).getUserId();
        Call<HistoryResponse> call = apiService.getUserHistory(userId);

        call.enqueue(new Callback<HistoryResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("HistoryFragment", "onResponse: " + response.body().getData());
                    if (response.body().getData().isEmpty()) {
                        txtHistoryEmpty.setVisibility(View.VISIBLE);
                    } else {
                        historyList.addAll(response.body().getData());
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Log.d("HistoryFragment", "onResponse:" + response.body());
                    txtHistoryEmpty.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "Belum ada histori saat ini", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {
                Log.e("HistoryFragment", "onFailure: " + t.getMessage());
                Toast.makeText(getActivity(), "Request failed", Toast.LENGTH_SHORT).show();
            }
        });

        // Menangani klik item di RecyclerView
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(HistoryResponse.HistoryData historyData) {
                // Intent ke DetailActivity dengan data makanan
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("foodName", historyData.getFoodName());
                intent.putExtra("foodInformation", historyData.getFoodInformation());
                intent.putExtra("calorie", String.valueOf(historyData.getTotalCalorie()));
                intent.putExtra("sugar", String.valueOf(historyData.getTotalSugar()));
                intent.putExtra("carbohydrate", String.valueOf(historyData.getTotalCarbohydrate()));
                intent.putExtra("fat", String.valueOf(historyData.getTotalFat()));
                intent.putExtra("protein", String.valueOf(historyData.getTotalProtein()));
                startActivity(intent);
            }
        });

        return view;
    }
}
