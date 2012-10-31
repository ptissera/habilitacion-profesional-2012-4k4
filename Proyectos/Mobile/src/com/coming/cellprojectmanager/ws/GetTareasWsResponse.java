package com.coming.cellprojectmanager.ws;

import java.util.List;

import com.coming.cellprojectmanager.modelo.TareaDto;
import com.google.gson.Gson;

public class GetTareasWsResponse {
	public WsError error;
	public List<TareaDto> tareas;
	
	public static GetTareasWsResponse fromJSon(String json) {
		Gson gson = new Gson();
		return (GetTareasWsResponse)gson.fromJson(json, GetTareasWsResponse.class);
	}
}
