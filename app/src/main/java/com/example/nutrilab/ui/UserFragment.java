package com.example.nutrilab.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nutrilab.R;
import com.example.nutrilab.data.retrofit.ApiService;
import com.example.nutrilab.data.retrofit.RetrofitClient;
import com.example.nutrilab.data.model.ProfileResponse;
import com.example.nutrilab.pref.SharedPrefManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment {
    private FloatingActionButton fabEdit;
    private Button btnLogout;
    private TextView txtUserFullname, txtUsername, txtUserEmail, txtUserGender, txtUserDOB, txtUserAllergies, txtUserHeight, txtUserWeight;

    private void initUI(View view) {
        txtUserFullname = view.findViewById(R.id.user_fullname);
        txtUsername = view.findViewById(R.id.user_username);
        txtUserEmail = view.findViewById(R.id.user_email);
        txtUserGender = view.findViewById(R.id.user_gender);
        txtUserDOB = view.findViewById(R.id.user_dob);
        txtUserAllergies = view.findViewById(R.id.user_allergies);
        txtUserHeight = view.findViewById(R.id.user_height);
        txtUserWeight = view.findViewById(R.id.user_weight);
        fabEdit = view.findViewById(R.id.fab_edit);
        btnLogout = view.findViewById(R.id.btn_logout);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        initUI(view);

        getUserProfile();

        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfilActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getContext()).removeUserId();

                Intent intent = new Intent(getContext(), LandingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }

    private void getUserProfile() {
        String userId = SharedPrefManager.getInstance(getActivity()).getUserId();
        ApiService apiService = RetrofitClient.getApiService();
        Call<ProfileResponse> call = apiService.getProfileUser(userId);

        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProfileResponse.ProfileData profileData = response.body().getData();
                    txtUserFullname.setText(profileData.getUser().getName());
                    txtUsername.setText(profileData.getUser().getName());
                    txtUserEmail.setText(profileData.getUser().getEmail());
                    txtUserGender.setText(profileData.getGender());
                    txtUserDOB.setText(profileData.getDateOfBirth());
                    txtUserAllergies.setText(profileData.getAllergies());
                    txtUserWeight.setText(String.valueOf(profileData.getWeight()));
                    txtUserHeight.setText(String.valueOf(profileData.getHeight()));
                } else {
                    Toast.makeText(getActivity(), "Failed with status code: " + response.code() + " and message: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.e("UserFragment", "onFailure: " + t.getMessage());
                Toast.makeText(getActivity(), "Request failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
