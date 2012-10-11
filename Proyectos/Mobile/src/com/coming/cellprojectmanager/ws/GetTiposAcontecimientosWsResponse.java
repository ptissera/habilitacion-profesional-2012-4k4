package com.coming.cellprojectmanager.ws;

import java.util.List;

import com.coming.cellprojectmanager.modelo.TipoAcontecimientoDto;
import com.google.gson.Gson;

public class GetTiposAcontecimientosWsResponse {
	public WsError error;
	public List<TipoAcontecimientoDto> tipos;
	
	public static GetTiposAcontecimientosWsResponse fromJSon(String json) {
		Gson gson = new Gson();
		return (GetTiposAcontecimientosWsResponse)gson.fromJson(json, GetTiposAcontecimientosWsResponse.class);
	}
}
