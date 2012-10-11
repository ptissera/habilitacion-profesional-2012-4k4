package com.coming.cellprojectmanager.ws;

import java.util.List;

import com.coming.cellprojectmanager.modelo.SitioDto;
import com.google.gson.Gson;

public class GetSitiosWsResponse {
	public WsError error;
	public List<SitioDto> sitios;
	
	public static GetSitiosWsResponse fromJSon(String json) {
		Gson gson = new Gson();
		return (GetSitiosWsResponse)gson.fromJson(json, GetSitiosWsResponse.class);
	}
}
