package com.coming.cellprojectmanager.modelo;

import com.google.gson.Gson;

public class TipoAcontecimientoDto {
	public Long id;
	public String nombre;
	
	public static TipoAcontecimientoDto fromJSon(String json) {
		TipoAcontecimientoDto tipo = null;
		Gson gson = new Gson();
		tipo = (TipoAcontecimientoDto)gson.fromJson(json.toString(), TipoAcontecimientoDto.class);
		return tipo;
	}
}
