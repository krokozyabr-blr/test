package com.example.test.networking;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PhotoGetResponse extends AbstractResponse{

	PhotoResponse response;
	
	PhotoGetResponse(){
		response = new PhotoResponse();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	void parseSuccess(String body) throws Exception {
		
		Type collectionType = new TypeToken<List<Photo>>(){}.getType();
		ArrayList<Photo> lcs = (ArrayList<Photo>) new Gson().fromJson(body , collectionType);
		response.setResponse(lcs);
	}
	
	public PhotoResponse getResponse(){
		return response;
	}
}
