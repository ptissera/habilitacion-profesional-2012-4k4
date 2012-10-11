package com.coming.cellprojectmanager.modelo;

import java.util.Map;

import com.google.gson.Gson;

public class TransicionesDeTareasDto {
	public Map<String, String> transicionesValidas;
	
	public static TransicionesDeTareasDto fromJSon(String json) {
		TransicionesDeTareasDto transiciones = null;
		Gson gson = new Gson();
		transiciones = (TransicionesDeTareasDto)gson.fromJson(json.toString(), TransicionesDeTareasDto.class);
		return transiciones;
	}
}
