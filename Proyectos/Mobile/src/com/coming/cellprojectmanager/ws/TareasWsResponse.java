package com.coming.cellprojectmanager.ws;

import java.util.List;

import com.coming.cellprojectmanager.modelo.SitioDto;
import com.coming.cellprojectmanager.modelo.TareaDto;
import com.google.gson.Gson;

public class TareasWsResponse {
	public Error error;
	public SitioDto sitio;
	public List<TareaDto> tareas;
	
	public static TareasWsResponse fromJSon(String json) {
		Gson gson = new Gson();
		return (TareasWsResponse)gson.fromJson(json, TareasWsResponse.class);
	}
}
