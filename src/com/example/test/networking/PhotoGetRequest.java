package com.example.test.networking;

import java.io.InputStream;

public class PhotoGetRequest extends GetSpecHttpsRequest<PhotoGetResponse>{
	
	public PhotoGetRequest() {
		super(PhotoGetResponse.class);
	}

	@Override
	String composeUrl() {
		return "https://s3-us-west-2.amazonaws.com/wirestorm/assets/response.json";
	}

	@Override
	PhotoGetResponse parse(InputStream is) throws Exception {
		String body = readBody(is);
		PhotoGetResponse response = new PhotoGetResponse();
		response.parseSuccess(body);
		return response;
	}

}
