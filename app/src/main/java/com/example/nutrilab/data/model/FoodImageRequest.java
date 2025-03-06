package com.example.nutrilab.data.model;

public class FoodImageRequest {
    private String userId;
    private String base64image;

    public FoodImageRequest(String userId, String base64image) {
        this.userId = userId;
        this.base64image = base64image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBase64image() {
        return base64image;
    }

    public void setBase64image(String base64image) {
        this.base64image = base64image;
    }
}
