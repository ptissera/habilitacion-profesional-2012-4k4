package com.coming.cellprojectmanager.ws;

import com.google.gson.Gson;

public class PostAcontecimientoWsResponse {
	public WsError error;
	
	public static PostAcontecimientoWsResponse fromJSon(String json) {
		Gson gson = new Gson();
		return (PostAcontecimientoWsResponse)gson.fromJson(json, PostAcontecimientoWsResponse.class);
	}
}
