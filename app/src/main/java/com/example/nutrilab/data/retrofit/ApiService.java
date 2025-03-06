package com.example.nutrilab.data.retrofit;

import com.example.nutrilab.data.model.CreateProfileRequest;
import com.example.nutrilab.data.model.CreateProfileResponse;
import com.example.nutrilab.data.model.EditProfileRequest;
import com.example.nutrilab.data.model.EditProfileResponse;
import com.example.nutrilab.data.model.FoodImageRequest;
import com.example.nutrilab.data.model.FoodRecomendation;
import com.example.nutrilab.data.model.FoodRequest;
import com.example.nutrilab.data.model.FoodResponse;
import com.example.nutrilab.data.model.HistoryResponse;
import com.example.nutrilab.data.model.LoginRequest;
import com.example.nutrilab.data.model.LoginResponse;
import com.example.nutrilab.data.model.ProfileResponse;
import com.example.nutrilab.data.model.ProgressNutritionResponse;
import com.example.nutrilab.data.model.RegisterRequest;
import com.example.nutrilab.data.model.RegisterResponse;
import com.example.nutrilab.data.model.TotalNutritionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("/food/nutrition")
    Call<FoodResponse> trackFood(@Body FoodRequest foodRequest);

    @GET("/profile/nutrition/{userId}")
    Call<TotalNutritionResponse> getTotalNutrition(@Path("userId") String userId);

    @GET("/profile/nutrition/progress/{userId}")
    Call<ProgressNutritionResponse> getProgressNutrition(@Path("userId") String userId);

    @GET("/profile/{userId}")
    Call<ProfileResponse> getProfileUser(@Path("userId") String userId);

    @GET("/food/recommendation/{userId}")
    Call<FoodRecomendation> getRecommendation(@Path("userId") String userId);

    @GET("/history/{userId}")
    Call<HistoryResponse> getUserHistory(@Path("userId") String userId);

    @POST("/auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("/auth/register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @POST("/profile")
    Call<CreateProfileResponse> createProfile(@Body CreateProfileRequest CreateProfileRequest);

    @PATCH("/profile/{userId}")
    Call<EditProfileResponse> editProfile(@Path("userId") String userId, @Body EditProfileRequest editProfileRequest);

    @POST("/food/nutrition/image")
    Call<FoodResponse> trackFoodImage(@Body FoodImageRequest foodImageRequest);
}
