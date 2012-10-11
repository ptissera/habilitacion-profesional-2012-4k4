package com.coming.cellprojectmanager.ws;

import com.google.gson.Gson;

public class GetLoginWsResponse {
	public WsError error;
	
	public static GetLoginWsResponse fromJSon(String json) {
		Gson gson = new Gson();
		return (GetLoginWsResponse)gson.fromJson(json, GetLoginWsResponse.class);
	}

}
