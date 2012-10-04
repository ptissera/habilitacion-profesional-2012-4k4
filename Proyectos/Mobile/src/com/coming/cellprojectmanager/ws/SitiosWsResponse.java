package com.coming.cellprojectmanager.ws;

import java.util.List;

import com.coming.cellprojectmanager.modelo.SitioDto;
import com.google.gson.Gson;

public class SitiosWsResponse {
	public Error error;
	public List<SitioDto> sitios;
	
	public static SitiosWsResponse fromJSon(String json) {
		Gson gson = new Gson();
		return (SitiosWsResponse)gson.fromJson(json, SitiosWsResponse.class);
	}
}
