package com.example.nutrilab.data.model;

import java.util.List;

public class NewsResponse{
	private List<DataNewsItem> data;
	private String message;
	private int status;

	public List<DataNewsItem> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public int getStatus(){
		return status;
	}
}