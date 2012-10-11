package com.coming.cellprojectmanager.ws;

import java.util.List;

import com.coming.cellprojectmanager.modelo.AcontecimientoDto;
import com.google.gson.Gson;

public class GetAcontecimientosWsResponse {
	public WsError error;
	public List<AcontecimientoDto> acontecimientos;
	
	public static GetAcontecimientosWsResponse fromJSon(String json) {
		Gson gson = new Gson();
		return (GetAcontecimientosWsResponse)gson.fromJson(json, GetAcontecimientosWsResponse.class);
	}
}
