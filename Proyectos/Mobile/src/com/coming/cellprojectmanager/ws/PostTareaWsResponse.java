package com.coming.cellprojectmanager.ws;

import com.google.gson.Gson;

public class PostTareaWsResponse {
	public WsError error;
	
	public static PostTareaWsResponse fromJSon(String json) {
		Gson gson = new Gson();
		return (PostTareaWsResponse)gson.fromJson(json, PostTareaWsResponse.class);
	}
}
