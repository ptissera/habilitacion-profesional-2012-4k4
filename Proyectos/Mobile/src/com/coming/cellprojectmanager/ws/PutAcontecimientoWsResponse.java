package com.coming.cellprojectmanager.ws;

import com.google.gson.Gson;

public class PutAcontecimientoWsResponse {
	public WsError error;
	
	public static PutAcontecimientoWsResponse fromJSon(String json) {
		Gson gson = new Gson();
		return (PutAcontecimientoWsResponse)gson.fromJson(json, PutAcontecimientoWsResponse.class);
	}
}
